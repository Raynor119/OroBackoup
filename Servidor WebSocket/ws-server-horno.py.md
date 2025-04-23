# 📝 **Informe Funcional del Script WebSocket del Horno Inteligente**

## 🚀 Inicio del Servidor

Al ejecutar el script:

- Se verifica la conexión con la base de datos MySQL `horno_monitoring`.
- Si la conexión es exitosa, se lanza un **servidor WebSocket** en la dirección `ws://0.0.0.0:8080`.
- Se imprime en consola:
~~~bash
🟢 Conexión a la base de datos exitosa.
🚀 Servidor WebSocket iniciado en ws://0.0.0.0:8080/
~~~
## 🔗 Conexión de Clientes

El sistema permite la conexión de **dos tipos de clientes**:

- 📱 **Aplicación móvil** (`{"type": "mobile_app"}`)
- 🖥️ **ESP32** (`{"type": "esp32"}`)

Cuando un cliente se conecta correctamente, se muestra:

- 📱 Aplicación móvil conectada.
- 🖥️ ESP32 conectado.

Cuando se desconectan, se muestra:

- 📱 Aplicación móvil desconectada.
- 🖥️ ESP32 desconectado.
## 🧠 Lógica de Comunicación

### 📱 Desde la App Móvil

La app puede enviar comandos como:

- **`{"command": "START"}`**
    - 🟢 Crea una nueva sesión en la base de datos (`measurement_sessions`).
    - 🔁 Informa al ESP32 que debe comenzar a medir enviando el texto `"START"`.
    - ✅ Se muestra en consola:
~~~bash
✅ Sesión iniciada. ID de sesión: [ID_GENERADO]
~~~

- **`{"command": "STOP"}`**
    - ⛔ Actualiza la sesión en la base de datos, marcándola como detenida.
	- 🔁 Informa al ESP32 que debe detener la medición enviando `"STOP"`.
	- ⚠️ Se muestra en consola:
~~~bash
⚠️ Sesión detenida. ID de sesión: [ID]
~~~

### 🖥️ Desde el ESP32

- El ESP32 envía directamente valores de temperatura como texto (ej: `"28.5"`).
- Estos valores:
    - 🌡️ Se guardan junto con la fecha y hora en `temperature_readings`.
    - 📤 Se notifica a la app móvil con el `session_id` actual.
    - Se imprime en consola:
~~~bash
🌡️ Lectura de temperatura: 28.5°C a las 2025-04-20 14:35:00
~~~

## 🧰 Gestión de Errores

- ❌ Si un cliente no se identifica correctamente, recibe un mensaje de error.
- ❌ Si el ESP32 envía un dato que no es numérico, se reporta como lectura inválida.
- ❌ Si ocurre un error al decodificar JSON desde la app, también se informa.

## 🛡️ Seguridad y Control

- Se utiliza un diccionario `clients` para asegurar que cada tipo de cliente esté bien identificado y se evite interferencia entre ellos.
- Cada nueva sesión queda registrada con:
    - Fecha/hora de inicio
    - Usuario asociado (user_id=1)
    - Estado: `active` o `stopped`

## ⚙️ Gestión del Servicio `ws-server-horno.service`

Puedes utilizar los siguientes comandos para controlar el servicio:​

### ▶️ Iniciar el servicio

~~~bash
sudo systemctl start ws-server-horno.service
~~~

### ⏹️ Detener el servicio

~~~bash
sudo systemctl stop ws-server-horno.service
~~~

### 🔄 Reiniciar el servicio

~~~bash
sudo systemctl restart ws-server-horno.service
~~~

### 📌 Habilitar el servicio al inicio del sistema

~~~bash
sudo systemctl enable ws-server-horno.service
~~~

### 🚫 Deshabilitar el servicio al inicio del sistema

~~~bash
sudo systemctl disable ws-server-horno.service
~~~

## 🔍 Verificación y Depuración

### 📊 Verificar el estado del servicio

~~~bash
sudo systemctl status ws-server-horno.service
~~~

Este comando te proporcionará información detallada sobre el estado actual del servicio, incluyendo si está activo, cualquier error reciente y más.​

### 📄 Ver los registros en tiempo real

~~~bash
journalctl -u ws-server-horno.service -f
~~~

Este comando te permitirá monitorear los registros generados por el servicio en tiempo real, lo cual es útil para depuración y seguimiento de eventos.

# 💻 Script

el script es el siguiente `ws-server-horno.py` que se aloja en la siguiente dirección `/home/server/ws-server/ws-server-horno.py`

~~~python
import asyncio
import websockets
import json
import pymysql
from datetime import datetime
from rich.console import Console

# Crear una instancia de Console para imprimir con estilo
console = Console()

# Configuración de la base de datos
DB_CONFIG = {
    'host': 'localhost',
    'user': 'ServerWS',
    'password': '67895421d',
    'database': 'horno_monitoring'
}

# Diccionarios para almacenar las conexiones
clients = {
    'esp32': None,
    'mobile_app': None
}

# Variable para almacenar el ID de la sesión actual
current_session_id = None

# Función para verificar la conexión a la base de datos
def check_db_connection():
    try:
        connection = pymysql.connect(**DB_CONFIG)
        connection.close()
        return True
    except pymysql.MySQLError as e:
        console.print(f"[bold red]❌ Error de conexión a la base de datos: {e}[/bold red]")
        return False

# Verificar la conexión a la base de datos al iniciar el script
if check_db_connection():
    console.print("[bold green]🟢 Conexión a la base de datos exitosa.[/bold green]")
else:
    console.print("[bold red]❌ No se pudo conectar a la base de datos. El servidor no se iniciará.[/bold red]")
    exit(1)

async def handle_connection(websocket):
    global current_session_id

    try:
        # Esperar el mensaje de identificación
        message = await websocket.recv()
        data = json.loads(message)
        client_type = data.get('type')

        if client_type not in clients:
            await websocket.send(json.dumps({'error': 'Tipo de cliente no reconocido'}))
            return

        clients[client_type] = websocket

        # Imprimir mensaje con icono y color según el tipo de cliente
        if client_type == 'mobile_app':
            console.print("[bold cyan]📱 Aplicación móvil conectada.[/bold cyan]")
        elif client_type == 'esp32':
            console.print("[bold yellow]🖥️ ESP32 conectado.[/bold yellow]")

        # Escuchar mensajes del cliente
        async for message in websocket:
            if client_type == 'mobile_app':
                await handle_mobile_app_message(message)
            elif client_type == 'esp32':
                await handle_esp32_message(message)

    except websockets.exceptions.ConnectionClosed:
        # Imprimir mensaje con icono y color según el tipo de cliente
        if client_type == 'mobile_app':
            console.print("[bold red]📱 Aplicación móvil desconectada.[/bold red]")
        elif client_type == 'esp32':
            console.print("[bold red]🖥️ ESP32 desconectado.[/bold red]")
        clients[client_type] = None

async def handle_mobile_app_message(message):
    global current_session_id

    try:
        data = json.loads(message)
        command = data.get('command')

        if command == 'START':
            # Crear una nueva sesión en la base de datos
            connection = pymysql.connect(**DB_CONFIG)
            with connection.cursor() as cursor:
                sql = "INSERT INTO measurement_sessions (user_id, start_timestamp, status) VALUES (%s, %s, %s)"
                cursor.execute(sql, (1, datetime.now(), 'active'))
                current_session_id = cursor.lastrowid
                connection.commit()
            connection.close()

            console.print(f"[bold green]✅ Sesión iniciada. ID de sesión: {current_session_id}[/bold green]")

            # Notificar al ESP32 que inicie la medición
            if clients['esp32']:
                await clients['esp32'].send('START')

        elif command == 'STOP':
            # Actualizar la sesión en la base de datos
            connection = pymysql.connect(**DB_CONFIG)
            with connection.cursor() as cursor:
                sql = "UPDATE measurement_sessions SET end_timestamp = %s, status = %s WHERE id = %s"
                cursor.execute(sql, (datetime.now(), 'stopped', current_session_id))
                connection.commit()
            connection.close()

            console.print(f"[bold yellow]⚠️ Sesión detenida. ID de sesión: {current_session_id}[/bold yellow]")

            # Notificar al ESP32 que detenga la medición
            if clients['esp32']:
                await clients['esp32'].send('STOP')

        else:
            console.print("[bold yellow]⚠️ Comando no reconocido.[/bold yellow]")

    except json.JSONDecodeError:
        console.print("[bold red]❌ Error al decodificar el mensaje JSON.[/bold red]")

async def handle_esp32_message(message):
    global current_session_id

    try:
        temperature = float(message)
        timestamp = datetime.now()

        # Insertar la lectura en la base de datos
        connection = pymysql.connect(**DB_CONFIG)
        with connection.cursor() as cursor:
            sql = "INSERT INTO temperature_readings (session_id, timestamp, temperature) VALUES (%s, %s, %s)"
            cursor.execute(sql, (current_session_id, timestamp, temperature))
            connection.commit()
        connection.close()

        console.print(f"[bold cyan]🌡️ Lectura de temperatura: {temperature}°C a las {timestamp}[/bold cyan]")

        # Notificar a la aplicación móvil con el ID de la sesión
        if clients['mobile_app']:
            await clients['mobile_app'].send(json.dumps({'session_id': current_session_id}))

    except ValueError:
        console.print("[bold red]❌ Dato de temperatura no válido recibido del ESP32.[/bold red]")

async def main():
    # Iniciar el servidor WebSocket
    async with websockets.serve(handle_connection, '0.0.0.0', 8080):
        console.print("[bold magenta]🚀 Servidor WebSocket iniciado en ws://0.0.0.0:8080/[/bold magenta]")
        await asyncio.Future()  # Mantener el servidor en ejecución indefinidamente

if __name__ == "__main__":
    asyncio.run(main())
~~~

