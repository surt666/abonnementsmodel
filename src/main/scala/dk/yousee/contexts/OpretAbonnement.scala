package dk.yousee.contexts

import java.util.Date
import dk.yousee.abonnement.{Abonnement, Periode, LeveringsAftale}
import dk.yousee.roles.{BestilFraLager, Provisionering}

/**
 * Created by IntelliJ IDEA.
 * User: sla
 * Date: May 11, 2010
 * Time: 6:52:21 PM
 */

class OpretAbonnement(abonId : Int, juridisk : Int, betaler : Int, forbruger : Int, produktId : Int) {
  //todo find ud af om produkt er bundle og opret tilsvarende Leverings aftaler
  val pris = 100.00 //todo find pris fra produktid
  val leveringsPeriode = new Periode(new Date,new Date) //todo beregn
  val faktureringsPeriode = new Periode(new Date,new Date) //todo beregn
  val properties = new scala.collection.mutable.HashMap[String,String]
  val provisionering = true //todo find ud af om produkt skal provisioneres
  val bestilFraLager = true //todo find ud af om hardware skal bestilles
  
  val leveringsaftale = opretLeveringsAftale(abonId,betaler,forbruger,produktId,provisionering,bestilFraLager,leveringsPeriode,properties)
  val abonnement = new Abonnement(abonId,juridisk,faktureringsPeriode,leveringsaftale :: Nil, pris, 0.0)
  abonnement.persist

  def opretLeveringsAftale(abonId : Int, betaler : Int, forbruger : Int, produktId : Int, provisionering : Boolean, bestilFraLager : Boolean, leveringsPeriode : Periode, properties : scala.collection.mutable.Map[String,String]) : LeveringsAftale = {
    if (provisionering && bestilFraLager) {
      var l = new LeveringsAftale(abonId,produktId,leveringsPeriode, forbruger, betaler, properties) with Provisionering with BestilFraLager
      l.bestilFraIRIS
      //todo bestem hvor der skal provisioneres
      l.provisionerStalone
      l
    } else if (provisionering && !bestilFraLager) {
      val l = new LeveringsAftale(abonId,produktId,leveringsPeriode, forbruger, betaler, properties) with Provisionering
      //todo bestem hvor der skal provisioneres
      l.provisionerSigma
      l
    } else if (provisionering && !bestilFraLager) {
      val l = new LeveringsAftale(abonId,produktId,leveringsPeriode, forbruger, betaler, properties) with BestilFraLager
      l.bestilFraIRIS
      l
    } else {
      new LeveringsAftale(abonId,produktId,leveringsPeriode, forbruger, betaler, properties)
    }
  }
}