<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>👾 ${title} 👾</title>
    <link href="https://fonts.googleapis.com/css?family=Roboto+Mono&display=swap" rel="stylesheet">
    <style>
        body {
            font-family: 'Roboto Mono', monospace;
            color: white;
            background-color: black;
        }

        .page-body {
            text-align: center;
            margin-top: 10%;
        }

        .form-container {
            display: flex;
            justify-content: center;
        }

        .form-container input {
            padding: 5px;

            color: white;
            font-size: 1rem;
            font-family: 'Roboto Mono', monospace;

            border: 1px solid white;
            background-color: transparent;
        }

        .form-container button[type="submit"] {
            width: 40%;
            margin-top: 20px;
            padding: 5px;

            color: white;
            font-family: 'Roboto Mono', monospace;
            font-weight: bold;
            text-transform: uppercase;
            letter-spacing: 1px;

            background-color: transparent;
            border: 1px dashed white;
            cursor: pointer;
        }
    </style>
</head>
<body>
<div class="page-body">
    <h1>
        ${welcomeTitle}
    </h1>
    <h2>
        ${welcomeBody}
    </h2>
    <div class="form-container">
        <form method="post" action="${pageContext.request.contextPath}/">
            <table>
                <tr>
                    <td>
                        <label for="name-input">Username:</label>
                    </td>
                    <td>
                        <input type="text" name="userName" id="name-input"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <label for="password-input">Password:</label>
                    </td>
                    <td>
                        <input type="password" name="userPassword" id="password-input"/>
                    </td>
                </tr>
            </table>
            <button type="submit">Submit</button>
        </form>
    </div>
</div>
</body>
</html>
