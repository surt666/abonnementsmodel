package dk.yousee.roles

import dk.yousee.events.ProvisioneringsEvent
import java.util.Date

/**
 * Created by IntelliJ IDEA.
 * User: sla
 * Date: May 11, 2010
 * Time: 6:50:54 PM
 */

trait Provisionering {
  def provisionerSigma(provNum : String) : Unit = {
    println("SIGMA " + provNum)
    val event = ProvisioneringsEvent("Sigma",provNum, new Date)
    queueEvent(event)
  }

  def provisionerStalone(provNum : String) : Unit = {
    println("Stalone " + provNum)
    val event = ProvisioneringsEvent("Stalone",provNum, new Date)
    queueEvent(event)
  }

  private def queueEvent(event : ProvisioneringsEvent) {
    //todo queue event
  }
 
}