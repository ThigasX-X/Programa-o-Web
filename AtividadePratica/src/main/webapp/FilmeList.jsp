<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Lista de Filmes Alugados</title>
<style>
body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background: linear-gradient(135deg, #333, #1c1c1c);
            margin: 0;
            padding: 0;
            color: #e0e0e0;
        }

        h1 {
            text-align: center;
            background: linear-gradient(135deg, #444, #222);
            color: #e0e0e0;
            padding: 20px 0;
            margin: 0;
            font-size: 1.8rem;
            letter-spacing: 1px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
        }

        .container {
            max-width: 1000px;
            margin: 40px auto;
            padding: 25px;
            background: #2b2b2b;
            border-radius: 10px;
            box-shadow: 0 8px 20px rgba(0, 0, 0, 0.2);
            animation: fadeIn 0.5s ease-out;
        }

        label {
            display: block;
            font-size: 14px;
            font-weight: bold;
            margin-bottom: 10px;
            color: #cccccc;
        }

        input[type="text"] {
            width: calc(100% - 60px);
            padding: 12px;
            margin-right: 10px;
            border: 1px solid #444;
            border-radius: 6px;
            background-color: #1c1c1c;
            color: #e0e0e0;
            box-sizing: border-box;
            transition: border 0.3s ease, box-shadow 0.3s ease;
        }

        input[type="text"]:focus {
            border-color: #888;
            outline: none;
            box-shadow: 0 0 5px rgba(136, 136, 136, 0.5);
        }

        button[type="submit"] {
            background: linear-gradient(135deg, #555, #333);
            color: #e0e0e0;
            padding: 10px 15px;
            border: none;
            border-radius: 6px;
            cursor: pointer;
            transition: background 0.3s ease;
            font-weight: bold;
        }

        button[type="submit"]:hover {
            background: linear-gradient(135deg, #666, #444);
        }

        a {
            text-decoration: none;
            font-weight: bold;
            transition: color 0.3s ease;
        }

        a:hover {
            color: #bbb;
        }

        .new-filme {
            display: inline-block;
            background: linear-gradient(135deg, #555, #333);
            color: #e0e0e0;
            padding: 12px 20px;
            border-radius: 5px;
            text-align: center;
            margin-bottom: 20px;
            font-size: 14px;
            transition: background 0.3s ease;
        }

        .new-filme:hover {
            background: linear-gradient(135deg, #666, #444);
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
            background-color: #1c1c1c;
        }

        th, td {
            padding: 15px;
            text-align: left;
            border-bottom: 1px solid #444;
        }

        th {
            background-color: #333;
            color: #cccccc;
            font-size: 14px;
        }

        td {
            font-size: 14px;
            color: #e0e0e0;
        }

        tr:hover {
            background-color: #292929;
            transition: background 0.2s ease;
        }

        .actions a {
            margin-right: 10px;
            color: #888;
            font-size: 14px;
        }

        .actions a:hover {
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

	<h1>Lista de Filmes Alugados</h1>




	<div class="container">
		<a href="filme?action=new" class="new-filme">Novo Filme</a> 
		<label for="buscar">Pesquise pelo Filme Alugado: </label> 
		
		    <form action="filme" method="get" style="text-align: center; margin-bottom: 20px;">
			<input type="hidden" name="action" value="search">
			<input type="text" name="query" placeholder="Digite sua pesquisa...">
			<button type="submit">Buscar</button>
			</form>
			
		<table>
			<thead>
				<tr>
					<th>ID</th>
					<th>Nome</th>
					<th>Descrição</th>
					<th>Data de Inclusão</th>
					<th>Ações</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="filme" items="${listFilme}">
					<tr>
						<td>${filme.id}</td>
						<td>${filme.nome}</td>
						<td>${filme.descricao}</td>
						<td>${filme.data_inclusao}</td>
						<td class="actions"><a
							href="filme?action=edit&id=${filme.id}">Editar</a> | <a
							href="filme?action=delete&id=${filme.id}">Deletar</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>