<?php

/*
 * BUGGGGGG
 */
namespace System;
use System\BaseController; 


// Class permettant le changement de page
class Home extends BaseController{
    
    // Page défaut
    public function index() {
        return $this->view->render('default');
    }
    
    // Page sio
    public function sio() {
        return $this->view->render('sio');
    }
}
