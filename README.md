# Demo
      
### Run


      $ ./gradlew clean bootRun
      
### Probar el servicio con un cliente de linea de comandos (CLI)

Se recomienda usar [`grpcc`](https://github.com/njpatel/grpcc) para poder probar el servicio en la linea de comandos. Se instala como un modulo de Node.



Una vez con `grpcc` instalado, se puede proceder a probar.

Iniciar cliente:

      $ grpcc -p src/main/proto/HelloService.proto -a 127.0.0.1:6565 -s HelloService -i
      
* Obtener los saludos


      $ var saludos = client.sayHello({name: "Domingo"})
      
      
* Para obtener el stream de datos
     
      $ saludos.on('data', function(p) { console.log(p); });
      
* Para saber cuando se termino el stream

      $ saludos.on('end', function() { console.log("No hay mas saludos"); });
      
      
* Hacer todo en una linea

      $ client.sayHello({name: "Domingo"}).on('data', function(p) { console.log(p); }).on('end', function() { console.log("No hay mas saludos"); });
      