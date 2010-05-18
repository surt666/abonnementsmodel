package dk.yousee.roles

import dk.yousee.events.ProvisioneringsEvent
import java.util.Date
import dk.yousee.model.LeveringsAftale

/**
 * Created by IntelliJ IDEA.
 * User: sla
 * Date: May 11, 2010
 * Time: 6:50:54 PM
 */

trait Provisionering {
  
  this : LeveringsAftale =>
  
  def provisionerSigma(provNum : String, date : Date) : Unit = {
    println("SIGMA " + provNum + "|" + date)
    val event = ProvisioneringsEvent("Sigma",provNum, date)
    queueEvent(event)
  }

  def provisionerStalone(provNum : String, date : Date) : Unit = {
    println("Stalone " + provNum + "|" + date)
    val event = ProvisioneringsEvent("Stalone",provNum, date)
    queueEvent(event)
  }

  private def queueEvent(event : ProvisioneringsEvent) {
    //todo queue event
  }
 
}