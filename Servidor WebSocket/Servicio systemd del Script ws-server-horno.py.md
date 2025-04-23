se creo una carpeta en la dirección `/etc/systemd/system/` y al crear un servicio le colocamos el nombre del servicio a la carpeta `ws-server-horno.service` por lo que usaremos el siguiente comando
~~~bash
sudo nano /etc/systemd/system/ws-server-horno.service
~~~

el contenido del servicio es el siguiente
~~~service
[Unit]
Description=Servidor WebSocket para el horno
After=network.target

[Service]
Type=simple
WorkingDirectory=/home/server/ws-server
Environment=VIRTUAL_ENV=/home/server/ws-server/venv
Environment=PATH=/home/server/ws-server/venv/bin:/usr/bin:/bin
ExecStart=/home/server/ws-server/venv/bin/python ws-server-horno.py
Restart=on-failure
User=server

[Install]
WantedBy=multi-user.target
~~~

luego se recarga todos los servicios con el siguiente comando
~~~bash
sudo systemctl daemon-reload
~~~

se habilita para su ejecución cuando el OS arranque

~~~bash
sudo systemctl enable ws-server-horno.service
~~~

y se arranca el servicio

~~~bash
sudo systemctl start ws-server-horno.service
~~~

se puede observar el estado del servicio con

~~~bash
sudo systemctl status ws-server-horno.service
~~~

y se puede observar una depuración completa con

~~~bash
sudo journalctl -u ws-server-horno.service -f
~~~

lo que nos mostrara de la siguiente forma

~~~bash
server@MiniPC:~/ws-server$ sudo journalctl -u ws-server-horno.service -f
abr 20 17:14:03 MiniPC python[4438]: ✅ Sesión iniciada. ID de sesión: 3
abr 20 17:14:03 MiniPC python[4438]: 🌡️ Lectura de temperatura: 24.0°C a las 2025-04-20 17:14:03.310241
abr 20 17:14:05 MiniPC python[4438]: 🌡️ Lectura de temperatura: 24.25°C a las 2025-04-20 17:14:05.306763
abr 20 17:14:07 MiniPC python[4438]: 🌡️ Lectura de temperatura: 24.25°C a las 2025-04-20 17:14:07.310214
abr 20 17:14:09 MiniPC python[4438]: 🌡️ Lectura de temperatura: 24.25°C a las 2025-04-20 17:14:09.342383
abr 20 17:14:11 MiniPC python[4438]: 🌡️ Lectura de temperatura: 23.75°C a las 2025-04-20 17:14:11.320307
abr 20 17:14:13 MiniPC python[4438]: 🌡️ Lectura de temperatura: 24.0°C a las 2025-04-20 17:14:13.329095
abr 20 17:14:15 MiniPC python[4438]: 🌡️ Lectura de temperatura: 24.0°C a las 2025-04-20 17:14:15.326062
abr 20 17:14:17 MiniPC python[4438]: 🌡️ Lectura de temperatura: 24.0°C a las 2025-04-20 17:14:17.341210
abr 20 17:14:18 MiniPC python[4438]: ⚠️ Sesión detenida. ID de sesión: 3
~~~
