<?php

namespace System;
use System\View;

/**
 * Classe Base Controller
 * @author Fabrice
 */
class BaseController 
{
    // VariaBLES
    public $view;
    public $url;
     
    // Fonction
    public function __construct()
    {
        //Instanciation de $view
        $this->view = new View();      
        //On récupere l'url avec la méthode getUrl
        $this->url->getUrl();
        /* Whoops*/
        if (ENVIRONMENT == 'development') 
        {	
            $whoops = new \Whoops\Run;	
            $whoops->pushHandler(new \Whoops\Handler\PrettyPageHandler);	
            $whoops->register();
        }
    }

    /**
     * Filtrage de l'URL
     */     
    protected function getUrl() 
    {	
	$url = isset($_SERVER['REQUEST_URI']) ? rtrim($_SERVER['REQUEST_URI'], '/') : NULL;
        $url = filter_var($url, FILTER_SANITIZE_URL);	
	return $this->url = $url;	
    }
      
}
