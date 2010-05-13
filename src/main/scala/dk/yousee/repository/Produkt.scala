package dk.yousee.repository

import collection.mutable.Map

/**
 * Created by IntelliJ IDEA.
 * User: sla
 * Date: May 13, 2010
 * Time: 11:23:28 AM
 */

class Produkt(val id : Int, val navn : String, val pris : Double, val bundleProducts :List[Int], val properties : Map[Properties.Value,String])