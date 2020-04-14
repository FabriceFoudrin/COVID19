<?php

namespace App\Controllers;
use App\Models\Contact;

/**
 *
 * @author Fabrice
 */
class Contacts 
{
    
    /**
     *  Récup les contacts dans un tableau
     * @access liste contact
     */
    public function index(){
        
        $contact = new Contact();
        $contacts = $contact->getContacts();
    }
}
