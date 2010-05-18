package dk.yousee.roles

import dk.yousee.model.LeveringsAftale

/**
 * Created by IntelliJ IDEA.
 * User: sla
 * Date: May 11, 2010
 * Time: 6:51:41 PM
 */

trait BestilFraLager {
  
  this : LeveringsAftale =>

  def bestilFraIRIS(logistikNummer : String) : Unit = {
    println("IRIS " + logistikNummer)
  }
}