<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="charset" content="windows-1251">
<title>Create Student</title>
</head>
<body>
<div  style="font-size: 20px; text-align: center; padding: 30px; margin: 50px; border: 1px solid black; border-radius: 5px; display: flex; flex-direction: column; justify-content: center;">
<h3> Enter the data to create a student:</h3>
    <form method="post" action="createstudent">
    <label>
        <span>ID Student: </span>
        <input type="number" name="id"/><br><br>
    </label>
    <label>
        <span>First Name: </span>
        <input type="text" name="firstName"/><br><br>
    </label>
    <label>
        <span>Last Name: </span>
        <input type="text" name="lastName"/><br><br>
    </label>
    <input type="submit" value="Create!" />
</div>
</body>
</html>