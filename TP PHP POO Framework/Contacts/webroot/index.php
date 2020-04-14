<!DOCTYPE html>

<html>
    <head>
        <meta charset="UTF-8">
        <title></title>
    </head>
    <body>
        <?php
         if (file_exists('../vendor/autoload.php')) { require '../vendor/autoload.php';} else 
        {
            echo 'autoload n\'est pas chargé';
        }

	define('ENVIRONMENT', 'development');
	if (defined('ENVIRONMENT')) 
        {
		switch (ENVIRONMENT) 
                {
                    case 'development':
			error_reporting(E_ALL);
			break;

                    case 'production':
			error_reporting(0);
			break;
                    default:
			exit('L\'environnement de l\'application n\'a pas été défini correctement');
		}
	}
        

        
        $config = App\Config::get();
        
        
        
        
        new System\Route($config);
        ?>
    </body>
</html>
