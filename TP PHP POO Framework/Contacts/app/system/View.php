<?php

namespace System;

class View {

    public function render($path, $data = false)
    {       
        if ($data)
        {
            foreach($data as $key => $value)
            {             
                ${$key} = $value;              
            }
        }
        //Path vers les différente vue (ex erreur 404 , sio ) 
        $filepath = "../app/views/$path.php"; 
       
        if( file_exists($filepath)) 
        {
            require $filepath;
	} 
        else
        {
            die ("Vue : $path chemin non trouvé ");
        }
    }
}
