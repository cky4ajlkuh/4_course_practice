<html>
<head>
<meta charset="UTF-8">
<title>Create Textbook</title>
</head>
<body>
<div style="font-size: 20px; text-align: center; padding: 30px; margin: 50px; border: 1px solid black; border-radius: 5px; display: flex; flex-direction: column; justify-content: center;">
<h3> Enter the data to create a textbook: </h3>
    <form method="post" action="textbook">
    <label>
        <span>ID Textbook: </span>
        <input type="number" name="id"/><br><br>
    </label>
    <label>
        <span>Title: </span>
        <input type="text" name="title"/><br><br>
    </label>
    <label>
        <span>Student ID: </span>
        <input type="number" name="student_id"/><br><br>
    </label>
    <input type="submit" value="Create!" />
</div>
</body>
</html>
