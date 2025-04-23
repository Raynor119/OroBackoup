el servidor linux posee la siguiente configuracion de linux y su correspondiente hardware 
~~~bash
neofetch
~~~
![](Pasted%20image%2020250420124006.png)
su sistema de interfaces de red es el siguiente
~~~bash
server@MiniPC:~$ ifconfig
enp1s0: flags=4099<UP,BROADCAST,MULTICAST>  mtu 1500
        ether 84:47:09:45:c4:eb  txqueuelen 1000  (Ethernet)
        RX packets 0  bytes 0 (0.0 B)
        RX errors 0  dropped 0  overruns 0  frame 0
        TX packets 0  bytes 0 (0.0 B)
        TX errors 0  dropped 0 overruns 0  carrier 0  collisions 0

enp2s0: flags=4163<UP,BROADCAST,RUNNING,MULTICAST>  mtu 1500
        inet6 2800:484:8d7e:1f00::1  prefixlen 128  scopeid 0x0<global>
        inet6 2800:484:8d7e:1f00:86b7:6891:d2f8:d09b  prefixlen 64  scopeid 0x0<global>
        inet6 fe80::49b2:8d3:fa2a:944a  prefixlen 64  scopeid 0x20<link>
        inet6 2800:484:8d7e:1f00:69c1:4941:bde3:6512  prefixlen 64  scopeid 0x0<global>
        inet6 2800:484:8d7e:1f00:5312:dc4f:1896:3ef7  prefixlen 64  scopeid 0x0<global>
        ether 84:47:09:45:c4:ed  txqueuelen 1000  (Ethernet)
        RX packets 340473  bytes 71288549 (71.2 MB)
        RX errors 0  dropped 7319  overruns 0  frame 0
        TX packets 319964  bytes 54130444 (54.1 MB)
        TX errors 0  dropped 0 overruns 0  carrier 0  collisions 0

lo: flags=73<UP,LOOPBACK,RUNNING>  mtu 65536
        inet 127.0.0.1  netmask 255.0.0.0
        inet6 ::1  prefixlen 128  scopeid 0x10<host>
        loop  txqueuelen 1000  (Bucle local)
        RX packets 230127  bytes 170877333 (170.8 MB)
        RX errors 0  dropped 0  overruns 0  frame 0
        TX packets 230127  bytes 170877333 (170.8 MB)
        TX errors 0  dropped 0 overruns 0  carrier 0  collisions 0

wlp3s0: flags=4163<UP,BROADCAST,RUNNING,MULTICAST>  mtu 1500
        inet 192.168.20.24  netmask 255.255.255.0  broadcast 192.168.20.255
        inet6 2800:484:8d7e:1f00:83b6:6c86:eb36:b6c7  prefixlen 64  scopeid 0x0<global>
        inet6 fe80::c857:1869:ac23:61d2  prefixlen 64  scopeid 0x20<link>
        inet6 2800:484:8d7e:1f00:b17b:8cb4:3e29:4720  prefixlen 64  scopeid 0x0<global>
        inet6 2800:484:8d7e:1f00:e58f:47b2:5998:2ed8  prefixlen 64  scopeid 0x0<global>
        inet6 2800:484:8d7e:1f00::2  prefixlen 128  scopeid 0x0<global>
        ether e8:bf:b8:d7:81:83  txqueuelen 1000  (Ethernet)
        RX packets 191364960  bytes 287613135212 (287.6 GB)
        RX errors 0  dropped 17  overruns 0  frame 0
        TX packets 9379435  bytes 1164512992 (1.1 GB)
        TX errors 0  dropped 0 overruns 0  carrier 0  collisions 0

ztyxaq2kdy: flags=4163<UP,BROADCAST,RUNNING,MULTICAST>  mtu 2800
        inet 172.30.100.218  netmask 255.255.0.0  broadcast 172.30.255.255
        inet6 fe80::bc42:9bff:feb8:3bc0  prefixlen 64  scopeid 0x20<link>
        ether be:42:9b:b8:3b:c0  txqueuelen 1000  (Ethernet)
        RX packets 65579  bytes 12885545 (12.8 MB)
        RX errors 0  dropped 0  overruns 0  frame 0
        TX packets 3014  bytes 568772 (568.7 KB)
        TX errors 0  dropped 0 overruns 0  carrier 0  collisions 0
~~~
posee 2 puertos ethernet y una interfaz wifi con los correspondientes nombre
- Interfaz de Ethernet
	- enp1s0
	- enp2s0
- Interfaz Wifi
	- wlp3s0
posee una conexion virtual usando una interfaz de ethernet virtual con ZeroTier la cuel tiene como nombre  `ztyxaq2kdy`  la cual se le asigno la siguiente IPv4  `172.30.100.218` 

Se utilizara el puerto ethernet como conexion a internet y con IP Fija la cual sera 

~~~IPv4
192.168.20.24
~~~
para la interfaz de ethernet
~~~Interfaz de Ethernet
enp2s0
~~~

# Configuración de IPv4 Fija por el Servidor por el Puerto enp2s0

primero debemos configurar el dispositivo de enrutamiento de paquetes que es el router modem de la vivienda, el cual en este caso es el 
~~~model/router
Marca: CastleNet Technology
Modelo: Infinity401
Proveedor: Claro
~~~

para acceder a dicho modem/router usamos la siguiente URL

 [https://192.168.20.1/login.asp](https://192.168.20.1/login.asp)
 
y las siguientes credenciales

~~~usuario
admin
~~~

~~~password
ZP9Lw$fGyUD
~~~

este router no posee la facilidad de configurar su rango del DHCP

por lo que se usara una IP muy alta por la que es muy poco probable que el DHCP llegue a usar

## Configuración de IP Fija 

Creamos una configuración en YAML par usarlo por netplan con el siguiente comando
~~~bash
sudo nano /etc/netplan/01-static-enp2s0.yaml
~~~
le agregamos la siguiente configuración a dicho archivo creado
~~~yaml
network:
  version: 2
  renderer: networkd
  ethernets:
    enp2s0:
      dhcp4: false
      addresses:
        - 192.168.20.254/24
      routes:
        - to: default
          via: 192.168.20.1
      nameservers:
        addresses:
          - 8.8.8.8
          - 2800:481:300::4
          - 2800:481:2300::4
~~~
lo guardamos y le damos permisos
~~~bash
sudo chmod 0600 /etc/netplan/01-static-enp2s0.yaml 
~~~
ahora activamos esa configuración con el siguiente comando
~~~bash
sudo netplan apply
~~~
verificamos que la IPv4 ya este aplicada
~~~bash
ip addr show enp2s0
~~~
y probamos conexión a internet con el siguiente comando de ping
~~~bash
ping -c 3 8.8.8.8 -I enp2s0
~~~
## Desactivando la Interfaz de red Wifi
modificamos el siguiente archivo
~~~bash
sudo nano /etc/NetworkManager/conf.d/default-wifi-powersave-on.conf
~~~
y le agregamos 
~~~conf
[keyfile]
unmanaged-devices=interface-name:wlp3s0
~~~
y luego recetemos el NetworkManager 
~~~bash
sudo systemctl reload NetworkManager
~~~

y ahora reiniciamos para verificar que todo esta listo
~~~bash
sudo reboot
~~~

por lo que tenemos la siguiente configuración de red del servidor 
~~~bash
server@MiniPC:~$ ifconfig
enp1s0: flags=4099<UP,BROADCAST,MULTICAST>  mtu 1500
        ether 84:47:09:45:c4:eb  txqueuelen 1000  (Ethernet)
        RX packets 0  bytes 0 (0.0 B)
        RX errors 0  dropped 0  overruns 0  frame 0
        TX packets 0  bytes 0 (0.0 B)
        TX errors 0  dropped 0 overruns 0  carrier 0  collisions 0

enp2s0: flags=4163<UP,BROADCAST,RUNNING,MULTICAST>  mtu 1500
        inet 192.168.20.254  netmask 255.255.255.0  broadcast 192.168.20.255
        inet6 2800:484:8d7e:1f00:8647:9ff:fe45:c4ed  prefixlen 64  scopeid 0x0<global>
        inet6 fe80::8647:9ff:fe45:c4ed  prefixlen 64  scopeid 0x20<link>
        inet6 2800:484:8d7e:1f00::7  prefixlen 128  scopeid 0x0<global>
        ether 84:47:09:45:c4:ed  txqueuelen 1000  (Ethernet)
        RX packets 1367  bytes 697844 (697.8 KB)
        RX errors 0  dropped 0  overruns 0  frame 0
        TX packets 1028  bytes 139122 (139.1 KB)
        TX errors 0  dropped 0 overruns 0  carrier 0  collisions 0

lo: flags=73<UP,LOOPBACK,RUNNING>  mtu 65536
        inet 127.0.0.1  netmask 255.0.0.0
        inet6 ::1  prefixlen 128  scopeid 0x10<host>
        loop  txqueuelen 1000  (Bucle local)
        RX packets 55  bytes 6629 (6.6 KB)
        RX errors 0  dropped 0  overruns 0  frame 0
        TX packets 55  bytes 6629 (6.6 KB)
        TX errors 0  dropped 0 overruns 0  carrier 0  collisions 0

ztyxaq2kdy: flags=4163<UP,BROADCAST,RUNNING,MULTICAST>  mtu 2800
        inet 172.30.100.218  netmask 255.255.0.0  broadcast 172.30.255.255
        inet6 fe80::bc42:9bff:feb8:3bc0  prefixlen 64  scopeid 0x20<link>
        ether be:42:9b:b8:3b:c0  txqueuelen 1000  (Ethernet)
        RX packets 0  bytes 0 (0.0 B)
        RX errors 0  dropped 0  overruns 0  frame 0
        TX packets 20  bytes 2359 (2.3 KB)
        TX errors 0  dropped 0 overruns 0  carrier 0  collisions 0
~~~
