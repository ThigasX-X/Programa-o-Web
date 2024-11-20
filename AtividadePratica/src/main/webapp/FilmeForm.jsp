<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Alugar Filme</title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background: linear-gradient(135deg, #333, #1c1c1c);
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            color: #e0e0e0;
        }

        h1 {
            text-align: center;
            color: #e0e0e0;
            font-size: 2rem;
            margin-bottom: 10px;
            text-shadow: 1px 1px 3px rgba(0, 0, 0, 0.5);
        }

        .form-container {
            width: 100%;
            max-width: 480px;
            background-color: #2b2b2b;
            padding: 30px;
            border-radius: 15px;
            box-shadow: 0 10px 30px rgba(0, 0, 0, 0.7);
            position: relative;
            animation: fadeIn 0.7s ease-out;
        }

        .form-header {
            text-align: center;
            margin-bottom: 20px;
        }

        .form-header h2 {
            font-size: 1.6rem;
            font-weight: bold;
            color: #cccccc;
        }

        label {
            font-weight: 600;
            margin-top: 15px;
            display: block;
            color: #cccccc;
        }

        input[type="text"], input[type="date"] {
            width: 100%;
            padding: 12px;
            margin: 8px 0 16px 0;
            border: 1px solid #444;
            border-radius: 5px;
            background-color: #1c1c1c;
            color: #e0e0e0;
            box-sizing: border-box;
            transition: border 0.3s ease, box-shadow 0.3s ease;
        }

        input[type="text"]:focus, input[type="date"]:focus {
            border-color: #888;
            outline: none;
            box-shadow: 0 0 6px rgba(136, 136, 136, 0.5);
        }

        input[type="submit"] {
            background: linear-gradient(135deg, #444, #222);
            color: #e0e0e0;
            padding: 12px;
            border: none;
            border-radius: 8px;
            cursor: pointer;
            width: 100%;
            font-size: 16px;
            margin-top: 15px;
            font-weight: bold;
            transition: background 0.3s ease, transform 0.2s ease;
        }

        input[type="submit"]:hover {
            background: linear-gradient(135deg, #555, #333);
            transform: scale(1.02);
        }

        .cancel-link {
            display: block;
            text-align: center;
            margin-top: 20px;
            color: #888;
            text-decoration: none;
            font-weight: 600;
            transition: color 0.3s ease;
        }

        .cancel-link:hover {
            color: #aaa;
            text-decoration: underline;
        }

        @keyframes fadeIn {
            from {
                opacity: 0;
                transform: translateY(-20px);
            }
            to {
                opacity: 1;
                transform: translateY(0);
            }
        }
    </style>
</head>
<body>
    <div class="form-container">
        <div class="form-header">
            <h1>Locadora de Filmes</h1>
            <h2>${filme != null ? 'Editar Aluguel de Filme' : 'Alugar Novo Filme'}</h2>
        </div>
        <form action="filme?action=${filme != null ? 'update' : 'insert'}" method="post">
            <input type="hidden" name="id" value="${filme != null ? filme.id : ''}" />
            
            <label for="nome">Nome do Filme:</label>
            <input type="text" id="nome" name="nome" value="${filme != null ? filme.nome : ''}" placeholder="Digite o nome do filme" />
            
            <label for="descricao">Descrição:</label>
            <input type="text" id="descricao" name="descricao" value="${filme != null ? filme.descricao : ''}" placeholder="Digite uma breve descrição" />
            
            <label for="data_inclusao">Data de Inclusão:</label>
            <input type="date" id="data_inclusao" name="data_inclusao" value="${filme != null ? filme.data_inclusao : ''}" />
            
            <input type="submit" value="Salvar" />
        </form>
        <a href="filme?action=list" class="cancel-link">Cancelar</a>
    </div>
</body>
</html>
