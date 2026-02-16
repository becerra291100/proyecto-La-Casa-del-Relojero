class seleccion:
    def __init__(self, seleccion="", sueldo="", nombre=""):
        self.seleccion = seleccion
        self.sueldo = sueldo
        self.nombre = nombre

    def pedir_datos(self):
        self.nombre = input("Su nombre es: ").title()
        self.seleccion = input("Trabaja en la selección de: ").title()
        self.sueldo = input("Su sueldo es de: ")

    def mostrar_info(self):
        print("\n--- Información general ---")
        print(f"Nombre: {self.nombre}")
        print(f"Selección: {self.seleccion}")
        print(f"Sueldo: {self.sueldo}")

class jugador(seleccion):
    def __init__(self, seleccion="", sueldo="", nombre="", posicion="", media="", numero="", lesiones=""):
        super().__init__(seleccion, sueldo, nombre)
        self.posicion = posicion
        self.media = media
        self.numero = numero
        self.lesiones = lesiones

    def pedir_datos(self):
        super().pedir_datos()
        self.posicion = input("Posición: ")
        self.media = input("Media: ")
        self.numero = input("Número: ")
        self.lesiones = input("Lesiones: ")

    def mostrar_info(self):
        super().mostrar_info()
        print(f"Posición: {self.posicion}")
        print(f"Media: {self.media}")
        print(f"Número: {self.numero}")
        print(f"Lesiones: {self.lesiones}")

class entrenador(seleccion):
    def __init__(self, seleccion="", sueldo="", nombre="", trofeos="", ManeraDeJugar=""):
        super().__init__(seleccion, sueldo, nombre)
        self.trofeos = trofeos
        self.ManeraDeJugar = ManeraDeJugar

    def pedir_datos(self):
        super().pedir_datos()
        self.trofeos = input("Sus trofeos son: ")
        self.ManeraDeJugar = input("Su manera de jugar es: ")

    def mostrar_info(self):
        super().mostrar_info()
        print(f"Trofeos: {self.trofeos}")
        print(f"Manera de jugar: {self.ManeraDeJugar}")

selecciones = {}
ventana = tk.Tk()


print("------Bienvenido a tu selección--------")
while True:
    opc = input("¿Quieres agregar?\n 1) Jugador\n 2) Entrenador\n 3) Mostrar Selecciones\n 4) Salir\n Opción: ")
    if opc == "1":
        print("CREANDO JUGADOR:")
        jugador1 = jugador()
        jugador1.pedir_datos()
        jugador1.mostrar_info()

        seleccion_nombre = jugador1.seleccion
        if seleccion_nombre not in selecciones:
            selecciones[seleccion_nombre] = []
        selecciones[seleccion_nombre].append(jugador1)

    elif opc == "2":
        print("\nCREANDO ENTRENADOR:")
        entrenador1 = entrenador()
        entrenador1.pedir_datos()
        entrenador1.mostrar_info()

        seleccion_nombre = entrenador1.seleccion
        if seleccion_nombre not in selecciones:
            selecciones[seleccion_nombre] = []
        selecciones[seleccion_nombre].append(entrenador1)

    elif opc == "3":
        print("\n------Selecciones------")
        for seleccion_nombre, lista in selecciones.items():
            print(f"\nSelección: {seleccion_nombre}")
            for persona in lista:
                persona.mostrar_info()

    elif opc == "4":
        print("Hasta luego")
        break

    else:
        print("Opción inválida. Intenta de nuevo.")
