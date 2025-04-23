# Prepara el entorno
asegurar de tener python 3.7 o superior instalado, se modifica usando el siguiente comando
~~~bash
python3 --version
~~~
# Crea un directorio para tu servidor y un entorno virtual
creamos el directorio con el siguiente comando
~~~bash
mkdir ~/ws-server && cd ~/ws-server
~~~
tenemos que instalar el paquete para crear entornos virtuales para esto usamos el siguiente comando
~~~bash
sudo apt install python3.10-venv
~~~
ahora creamos el entorno virtual el cual nos permite tener el proyecto de python con dependencias especificas sin que intervenga con otros proyectos 
~~~bash
python3 -m venv venv
~~~
y luego lo activamos con el siguiente comando
~~~bash
source venv/bin/activate
~~~
nos parecerá el terminal de la siguiente forma si esto fue correcto
~~~bash
server@MiniPC:~/ws-server$ source venv/bin/activate
(venv) server@MiniPC:~/ws-server$
~~~
# Instalar la librería de websocket
instalamos la librería que nos sirve tanto para servidor o para cliente en Python, para esto usamos el siguiente comando con el instalador pip
~~~bash
pip install websockets 
~~~
nos da lo siguiente al instalarlo correctamente 
~~~bash
Collecting websockets
  Downloading websockets-15.0.1-cp310-cp310-manylinux_2_5_x86_64.manylinux1_x86_64.manylinux_2_17_x86_64.manylinux2014_x86_64.whl (181 kB)
     ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━ 181.6/181.6 KB 2.4 MB/s eta 0:00:00
Installing collected packages: websockets
Successfully installed websockets-15.0.1
~~~
# Creación del Script del Servicio Websocket
Creamos el script con el nombre "server.py" con el siguiente comando
~~~bash
nano server.py
~~~
con el siguiente código
~~~python
#!/usr/bin/env python3
import asyncio
import websockets

async def echo(websocket):
    async for message in websocket:
        await websocket.send(message)

async def main():
    # Escucha en todas las interfaces IPv4 en el puerto 8080
    async with websockets.serve(echo, "0.0.0.0", 8080):
        print("WebSocket local escuchando en ws://0.0.0.0:8080/")
        await asyncio.Future()  # corre indefinidamente

if __name__ == "__main__":
    asyncio.run(main())
~~~

# Ejecución del script
lo ejecutamos con el siguiente comando
~~~bash
python server.py
~~~
y veremos lo siguiente
~~~bash
(venv) server@MiniPC:~/ws-server$ python server.py
WebSocket local escuchando en ws://0.0.0.0:8080/
~~~
