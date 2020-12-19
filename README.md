# Programacion Concurrente Trabajo Practico

Se requiere implementar en Java un programa concurrente que a partir de una secuencia
de bytes (potencialmente vacía), calcule un valor de 4 bytes (llamado nonce) tal que la
concatenación de las dos secuencias de bytes cumpla la dificultad mínima del algoritmo
de “Proof of Work” de la red de Bitcoin. El algoritmo de PoW consta en encontrar
un valor de nonce tal que los primeros n bits del resultado de aplicar la funci´on de
hash SHA-256 a la secuencia de bytes sean 0 (donde n está definido por el valor de
dificultad). La dificultad mínima de Bitcoin requiere los primeros 32 bits del resultado
en cero. Por simplicidad en este TP mediremos la dificultad en bytes en vez de bits,
siendo la dificultad mínima de Bitcoin 4 (i.e., los primeros 4 bytes deben ser 0).
El programa debe tomar por línea de comandos (1) la cantidad de threads a utilizar, (2)
una dificultad objetivo (en cantidad de bytes), y (3) una cadena de caracteres (opcional),
cuya representación como secuencia de bytes constituye el prefijo del valor a hashear
(siendo el nonce el sufijo). Considere usar el método getBytes() de la clase String para
obtener el arreglo de bytes a partir del input. El programa debe reportar (imprimiendo
por consola) el valor de nonce que logra el objetivo, o un mensaje declarando que tal
valor no existe para el input dado.
Se debe respetar la siguiente estructura:
1. Una clase ```Main``` con el punto de entrada del programa. El programa debe terminar o bien cuando se encontró un nonce que cumple la condición, o bien cuando
todos los valores del nonce (representables con 4 bytes) fueron evaluados sin encontrar ninguno que cumpla la condición impuesta por la dificultad. Al terminar
el programa debe informar el tiempo transcurrido desde el inicio de la ejecución.
2. Una clase ```Buffer``` (implementada como un monitor utilizando métodos synchronized) que actúa como una cola FIFO concurrente de capacidad acotada. Es decir,
bloquea a un lector intentando sacar un elemento cuando está vacía y bloquea a
un productor intentando agregar un elemento cuando está llena. La capacidad del
Buffer también debe ser parametrizable en el constructor del Buffer.
3. Una clase ```PowWorker``` que extiende de ```Thread``` y barre un rango de valores de
nonce buscando uno que cumpla la dificultad objetivo. Considere usar el método
```getInstance("SHA-256")``` de la clase ```MessageDigest``` para obtener un objeto con
el método ```digest``` que devuelve el resultado de la función de hash como un arreglo
de bytes. Los ```PowWorker``` deben consumir “unidades de trabajo” de una instancia
de ```Buffer``` compartida con Main, donde cada unidad de trabajo indica el rango de
valores a analizar. La clase Main tiene la responsabilidad de instanciar el Buffer
con capacidad máxima de 2 y agregar “unidades de trabajo equitativas” de a una
(i.e., si se pide usar 8 threads debe dividirse el espacio de posibles valores de nonce
en 8 partes iguales, o que a lo sumo difieran en 1).
4. Una clase ```ThreadPool```, que se encarga de instanciar e iniciar la cantidad de
```PowWorkers``` pedida por un usuario. La clase ```Main``` debe instanciar los ```PowWorkers```
a través del ```ThreadPool``` pasando como argumento el ```Buffer``` que le permitirá
enviar las “unidades de trabajo” a cada trabajador.
Al iniciar el programa se debe delegar la iniciación de los threads necesarios en la clase
```ThreadPool``` y luego introducir en el ```Buffer``` las “unidades de trabajo” de a una. Cada
```PowWorker``` en funcionamiento debe tomar unidades de trabajo del ```Buffer``` de a una y
verificar si hay un *nonce* válido en el rango dado. Cabe destacar que es inadmisible
utilizar una cantidad de threads menor a la solicitada por el usuario (aunque a la hora
de hacer pruebas se recomienda no usar más de 16 threads).

**Importante:** Además del código, se requiere un informe corto respetando el modelo
presentado a continuación, que incluya los tiempos de ejecución del programa con los
siguientes parámetros:
- **Threads:** 1, 2, 4, 6, 8 y 10
- **Dificultad:** 2 y 3
- **Prefijo:** vacío
Luego considerando la cantidad de threads con la que haya alcanzando la mayor eficiencia puede (opcionalmente) buscar un *nonce* para dificultad 4 (que llamaremos *“golden
nonce”*) y reportar el tiempo que le requirió encontrarlo.


## Informe
El informe, que es **condición necesaria para la aprobación del TP**, debe respetar
el siguiente formato:
1. **Autores:** Nombres, e-mail y número de legajo de los alumnos a los que pertenece
el trabajo (máximo 2 salvo excepciones acordadas con los docentes).
2. **Introducción:** Sección explicando el dominio del TP, el diseño del código y cualquier consideración adicional que considere relevante para la correcta interpretación de los resultados.
3. **Evaluación:** Sección explicando el proceso de evaluación con el objetivo de dificultad 2 y 3, incluyendo los detalles del equipo donde se ejecutan las pruebas (marca
de micro, cantidad de memoria disponible y versión del sistema operativo). En
esta sección deben incluirse dos tablas (una por cada dificultad) reportando los
tiempos en los que se logró encontrar el *nonce* adecuando para cada cantidad de
threads configurada, y el gráfico de la curva mostrando la progresión (con ambas
dificultades en superposición).
4. **Análisis:** Sección en la que debe hacerse un análisis de los datos obtenidos en la
evaluación, en particular destacando la cantidad de threads con la que se obtuvo
el tiempo de ejecución mínimo. En esta sección además puede reportar el tiempo
que tardó el mismo setting experimental en encontrar el *“golden nonce”* utilizando
la cantidad óptima de threads.
