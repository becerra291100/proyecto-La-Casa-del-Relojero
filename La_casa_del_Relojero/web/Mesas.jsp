<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Croquis de Mesas</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            text-align: center;
        }

        .container {
            display: flex;
            justify-content: center;
            margin-top: 50px;
        }

        .mesa {
            width: 50px;
            height: 50px;
            background-color: #3498db;
            margin: 10px;
            cursor: move;
        }

        #terrace {
            width: 500px;
            height: 400px;
            border: 2px solid #2ecc71;
            position: relative;
        }

        .mesa.draggable {
            position: absolute;
        }
    </style>
</head>
<body>
    <h2>Organiza las Mesas en la Terraza</h2>

    <div class="container">
        <!-- Mesas para arrastrar -->
        <div id="mesasDisponibles">
            <div class="mesa" draggable="true" id="mesa1"></div>
            <div class="mesa" draggable="true" id="mesa2"></div>
            <div class="mesa" draggable="true" id="mesa3"></div>
        </div>

        <!-- Terraza (Área de colocación) -->
        <div id="terrace"></div>
    </div>

    <script>
        // Permitir arrastrar elementos
        const mesas = document.querySelectorAll('.mesa');

        mesas.forEach(mesa => {
            mesa.addEventListener('dragstart', dragStart);
        });

        function dragStart(e) {
            e.dataTransfer.setData('text/plain', e.target.id);
            setTimeout(() => {
                e.target.style.display = 'none';
            }, 0);
        }

        mesas.forEach(mesa => {
            mesa.addEventListener('dragend', dragEnd);
        });

        function dragEnd(e) {
            e.target.style.display = 'block';
        }

        // Área de la terraza (zona de colocación)
        const terrace = document.getElementById('terrace');

        terrace.addEventListener('dragover', dragOver);
        terrace.addEventListener('drop', drop);

        function dragOver(e) {
            e.preventDefault();
        }

        function drop(e) {
            e.preventDefault();
            const id = e.dataTransfer.getData('text');
            const mesa = document.getElementById(id);

            // Obtener las coordenadas donde se suelta la mesa
            const rect = terrace.getBoundingClientRect();
            const x = e.clientX - rect.left;
            const y = e.clientY - rect.top;

            // Posicionar la mesa en las coordenadas del drop
            mesa.style.left = `${x - 25}px`; // Centrar en el punto de drop
            mesa.style.top = `${y - 25}px`;
            mesa.classList.add('draggable'); // Cambiar a posición absoluta

            terrace.appendChild(mesa); // Añadir la mesa a la terraza
        }
    </script>
</body>
</html>
