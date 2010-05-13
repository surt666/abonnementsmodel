package dk.yousee.roles

/**
 * Created by IntelliJ IDEA.
 * User: sla
 * Date: May 11, 2010
 * Time: 6:51:41 PM
 */

trait BestilFraLager {
  def bestilFraIRIS(logistikNummer : String) : Unit = {
    println("IRIS " + logistikNummer)
  }
}