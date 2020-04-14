<?php

namespace App;

class Config {

    public static function get() 
    {
       return[
           // Config de base
           'namespace' => 'App\Controllers',
           'default_controller' => 'Home',
           'default_method' => 'index',
           
           // Config accés bdd
           'db_type' 		=> 'pgsql',         'db_host'		=> 'localhost',
           'db_port'		=> '5432',          'db_name'		=> 'contacts',
           'db_username'        => 'fabrice',       'db_password'	=> 'root974',
       ];
}


}
