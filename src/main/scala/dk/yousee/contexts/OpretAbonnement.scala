package dk.yousee.contexts

import java.util.Date
import dk.yousee.abonnement.{Abonnement, Periode, LeveringsAftale}
import dk.yousee.roles.{BestilFraLager, Provisionering}
import dk.yousee.repository.{Properties, Produkt, ProduktRepo}
import collection.mutable.Map

/**
 * Created by IntelliJ IDEA.
 * User: sla
 * Date: May 11, 2010
 * Time: 6:52:21 PM
 */

class OpretAbonnement(abonId : Int, juridisk : Int, betaler : Int, forbruger : Int, produktId : Int) {
  //find ud af om produkt er bundle og opret tilsvarende Leverings aftaler
  val produkt = ProduktRepo.findProdukt(produktId)
  val leveringsAftaler = findAlleLeveringsAftaler(produkt,abonId,betaler,forbruger)
  val faktureringsPeriode = new Periode(new Date,new Date) //todo beregn
  val abonnement = new Abonnement(abonId,juridisk,faktureringsPeriode,leveringsAftaler,produkt.pris, 0.0)
  abonnement.persist

  private def opretLeveringsAftale(abonId : Int, betaler : Int, forbruger : Int, produktId : Int, provisioneringsSystem : String, provisioneringsNummer : String, bestilFraLager : Boolean, leveringsPeriode : Periode, properties : scala.collection.mutable.Map[String,String]) : LeveringsAftale = {
    if (provisioneringsSystem != "" && bestilFraLager) {
      var l = new LeveringsAftale(abonId,produktId,leveringsPeriode, forbruger, betaler, properties) with Provisionering with BestilFraLager
      l.bestilFraIRIS
      provisioneringsSystem match {
        case "Stalone" => l.provisionerStalone(provisioneringsNummer)
        case "Sigma" => l.provisionerSigma(provisioneringsNummer)
        case _ =>
      }
      l
    } else if (provisioneringsSystem != "" && !bestilFraLager) {
      val l = new LeveringsAftale(abonId,produktId,leveringsPeriode, forbruger, betaler, properties) with Provisionering
      provisioneringsSystem match {
        case "Stalone" => l.provisionerStalone(provisioneringsNummer)
        case "Sigma" => l.provisionerSigma(provisioneringsNummer)
        case _ =>
      }
      l
    } else if (provisioneringsSystem == "" && bestilFraLager) {
      val l = new LeveringsAftale(abonId,produktId,leveringsPeriode, forbruger, betaler, properties) with BestilFraLager
      l.bestilFraIRIS
      l
    } else {
      new LeveringsAftale(abonId,produktId,leveringsPeriode, forbruger, betaler, properties)
    }
  }

  private def findAlleLeveringsAftaler(p : Produkt,abonId : Int, betaler : Int, forbruger : Int) : List[LeveringsAftale] = {
    val leveringsPeriode = new Periode(new Date,new Date) //todo beregn
    val properties = new scala.collection.mutable.HashMap[String,String]
    val bestilFraLager = true //todo find ud af om hardware skal bestilles
    var leveringsAftaler = List[LeveringsAftale]()

    if (p.bundleProducts != Nil) {
      //we got a bundle
      for (bpId <- p.bundleProducts) {
        val bp = ProduktRepo.findProdukt(bpId)
        val provisioneringsSystem = findProperty(bp.properties,Properties.ProvSystem)
        val provisioneringsNummer = findProperty(bp.properties,Properties.ProvNum)
        leveringsAftaler = opretLeveringsAftale(abonId,betaler,forbruger,bp.id,provisioneringsSystem,provisioneringsNummer,bestilFraLager,leveringsPeriode,properties) :: leveringsAftaler
      }
    }
    val provisioneringsSystem = findProperty(p.properties,Properties.ProvSystem)
    val provisioneringsNummer = findProperty(p.properties,Properties.ProvNum)
    leveringsAftaler = opretLeveringsAftale(abonId,betaler,forbruger,p.id,provisioneringsSystem,provisioneringsNummer,bestilFraLager,leveringsPeriode,properties) :: leveringsAftaler
    leveringsAftaler
  }

  private def findProperty(properties : Map[Properties.Value,String], property : Properties.Value) : String = {
    if (properties != null && properties.keySet.contains(property))
      properties(property)
    else
      null
  }
}