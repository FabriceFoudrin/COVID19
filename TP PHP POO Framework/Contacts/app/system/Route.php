<?php 


namespace System;
use System\View; 


class Route {
    /* Constructeur de la classe Route qui se charge du fichier config 
    params $config 
     *      */
    public function __construct($config)
    {
        $url = explode('/', trim($_SERVER['REQUEST_URI'], '/'));                   
        
        $controller = !empty($url[0]) ? $url[0] : $config['default_controller'];            
        $method = !empty($url[1]) ? $url[1] : $config['default_method'];
        $args = !empty($url[2]) ? array_slice($url, 2) : array();
        $class = $config['namespace'] .'\\'. $controller; // Bug ici ?
        
        
        
        // Beug ici
        echo '<script>';
        echo 'console.log('. json_encode( $url[0] ) .')';
        echo '</script>';
        //


        // Instanciation de la page
        $classInstance = new $class;

        /* Appelle la fonction de rappel callback fournie avec les paramètres param_arr, rassemblés dans un tableau.*/
        call_user_func_array(array($classInstance, $method), $args);
    }

        // return 404
        public function not_found() 
        {
            $view = new View();
            return $view->render('404');
        }
}