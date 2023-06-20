<?php 

// Full-Stack code

$hostname='mysql.metropolia.fi'; 
$username='evgenh'; 
$password='biosfera333'; 
$dbname='evgenh'; 

// Connection to database
$con = new mysqli($hostname,$username,$password,$dbname); 

if ($con-> connect_errno) { 
die("Connection failed: " . $con->connect_error);
}

$sql = "SELECT id, country, population FROM vaest";
$result = $con->query($sql);

if ($result->num_rows > 0) {
  // output data of each row
  while($row = $result->fetch_assoc()) {
    echo " " . $row["id"]. " " . $row["country"]. " " . $row["population"]. "<br>";
  }
} else {
  echo "0 results";
}
$con->close();
?>

PHP - Delete
<?php
include_once 'db.php';
if(isset($_POST['submit']))
{    
     $country = $_POST['country'];
     $population = $_POST['population'];
     $sql = "DELETE FROM vaest (country, population)
     VALUES ('$country','$population')";
     if (mysqli_query($con, $sql)) {
        echo "New delete has been added successfully !";
     } else {
        echo "Error: " . $sql . ":-" . mysqli_error($con);
     }
     mysqli_close($con);
}
?>

PHP-Inseart 

<?php
include_once 'db.php';
if(isset($_POST['submit']))
{    
     $country = $_POST['country'];
     $population = $_POST['population'];
     $sql = "INSERT INTO vaest (country, population)
     VALUES ('$country','$population')";
     if (mysqli_query($con, $sql)) {
        echo "New record has been added successfully !";
     } else {
        echo "Error: " . $sql . ":-" . mysqli_error($con);
     }
     mysqli_close($con);
}
?>

HTML-CSS

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Country</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.css">
    <style type="text/css">
        .wrapper{
            width: 500px;
            margin: 0 auto;
        }
    </style>
    <script src="scripta.js"></script>
</head>
<body>
<h1>LARGEST COUNTRIES</h1>
	<h2>2021 World Population by Country</h2>
	<p>The current US Census Bureau world population estimate in June 2019 shows that the current global population is 7,577,130,400 people on earth, which far exceeds the world population of 7.2 billion from 2015. Our own estimate based on UN data shows the world's population surpassing 7.7 billion.

China is the most populous country in the world with a population exceeding 1.4 billion. It is one of just two countries with a population of more than 1 billion, with India being the second. As of 2018, India has a population of over 1.355 billion people, and its population growth is expected to continue through at least 2050. By the year 2030, the country of India is expected to become the most populous country in the world. This is because India’s population will grow, while China is projected to see a loss in population.

The next 11 countries that are the most populous in the world each have populations exceeding 100 million. These include the United States, Indonesia, Brazil, Pakistan, Nigeria, Bangladesh, Russia, Mexico, Japan, Ethiopia, and the Philippines. Of these nations, all are expected to continue to grow except Russia and Japan, which will see their populations drop by 2030 before falling again significantly by 2050.

Many other nations have populations of at least one million, while there are also countries that have just thousands. The smallest population in the world can be found in Vatican City, where only 801 people reside.

In 2018, the world’s population growth rate was 1.12%. Every five years since the 1970s, the population growth rate has continued to fall. The world’s population is expected to continue to grow larger but at a much slower pace. By 2030, the population will exceed 8 billion. In 2040, this number will grow to more than 9 billion. In 2055, the number will rise to over 10 billion, and another billion people won’t be added until near the end of the century. The current annual population growth estimates from the United Nations are in the millions - estimating that over 80 million new lives are added each year.</p>
    <img src="https://users.metropolia.fi/~evgenh/webuusi/world-political-maps.jpg" alt="World Political Map">
	<h2>What is next? You can add country and population into database</h2>
    <div class="wrapper">
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-12">
                    <div class="page-header">
                        <h2>Contact Form</h2>
                    </div>
                    <p>Please fill this form and submit to add country and population record to the database.</p>
                    <form action="insert.php" method="post">
                        <div class="form-group">
                            <label>Country</label>
                            <input type="text" name="country" class="form-control">
                        </div>
                        <div class="form-group">
                            <label>Population</label>
                            <input type="number" name="population" class="form-control">
                        </div>
                        <input type="submit" class="btn btn-primary" name="submit" value="Submit">
                    </form>

                     <p>Please fill this form and submit to delete country from the database by number.</p>
                    <form action="delete.php" method="post">
                        <div class="form-group">
                            <label>id</label>
                            <input type="number" name="id" class="form-control">
                        </div>
                        <input type="submit" class="btn btn-primary" name="submit" value="Submit">
                    </form>
                </div>
            </div>        
        </div>
    </div>
    <div id="country">
      <?php 
      require "db.php";
      if ($vaest) {
      foreach ($vaest as $v) { ?>
      <div class="country-wrap" data-id="<?=$p['country_id']?>">
        <div class="country-title"><?=$p['country_country']?></div>
        <div class="country-desc"><?=$p['country_population']?></div>
      </div>
      <?php } 
	  }?>
    </div>
</body>
</html>