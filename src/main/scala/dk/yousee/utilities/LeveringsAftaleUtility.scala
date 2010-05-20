package dk.yousee.utilities

import dk.yousee.repository.{Properties, Produkt, ProduktRepo}
import dk.yousee.roles.{Provisionering, BestilFraLager}
import java.util.Date
import collection.mutable.Map
import dk.yousee.model.{LeveringsAftaleStatus, Periode, LeveringsAftale}

/**
 * Created by IntelliJ IDEA.
 * User: sla
 * Date: May 13, 2010
 * Time: 4:20:55 PM
 */

object LeveringsAftaleUtility {
  def opretLeveringsAftale(abonId : Int, forbruger : Int, produktId : Int, provisioneringsSystem : String, provisioneringsNummer : String, logistikNummer : String, leveringsPeriode : Periode, properties : Map[Properties.Value,String], installationsId : Int) : LeveringsAftale = {
    val id = 1 //todo get id somewhere
    if (provisioneringsSystem != "" && logistikNummer != "") {
      var l = new LeveringsAftale(id,abonId,produktId,leveringsPeriode, forbruger, properties,LeveringsAftaleStatus.Aktiv,installationsId,None) with Provisionering with BestilFraLager
      l.bestilFraIRIS(logistikNummer)
      provisioneringsSystem match {
        case "Stalone" => l.provisionerStalone(provisioneringsNummer,new Date)
        case "Sigma" => l.provisionerSigma(provisioneringsNummer,new Date)
        case _ =>
      }
      l
    } else if (provisioneringsSystem != "" && logistikNummer == "") {
      val l = new LeveringsAftale(id,abonId,produktId,leveringsPeriode, forbruger, properties,LeveringsAftaleStatus.Aktiv,installationsId,None) with Provisionering
      provisioneringsSystem match {
        case "Stalone" => l.provisionerStalone(provisioneringsNummer,new Date)
        case "Sigma" => l.provisionerSigma(provisioneringsNummer,new Date)
        case _ =>
      }
      l
    } else if (provisioneringsSystem == "" && logistikNummer != "") {
      val l = new LeveringsAftale(id,abonId,produktId,leveringsPeriode, forbruger, properties,LeveringsAftaleStatus.Aktiv,installationsId,None) with BestilFraLager
      l.bestilFraIRIS(logistikNummer)
      l
    } else {
      new LeveringsAftale(id,abonId,produktId,leveringsPeriode, forbruger, properties,LeveringsAftaleStatus.Aktiv,installationsId,None)
    }
  }

  def findAlleLeveringsAftaler(p : Produkt,abonId : Int, forbruger : Int,installationsId : Int) : List[LeveringsAftale] = {
    val leveringsPeriode = new Periode(Some(new Date),None) //todo beregn
    val properties = Map[Properties.Value,String]()
    var leveringsAftaler = List[LeveringsAftale]()

    for (bpId <- findAlleBundleProdukter(p)) {
      val bp = ProduktRepo.findProdukt(bpId)
      val provisioneringsSystem = findProperty(bp.properties,Properties.ProvSystem)
      val provisioneringsNummer = findProperty(bp.properties,Properties.ProvNum)
      val logistikNummer = findProperty(bp.properties,Properties.LogistikNum)
      leveringsAftaler = opretLeveringsAftale(abonId,forbruger,bp.id,provisioneringsSystem.getOrElse(""),provisioneringsNummer.getOrElse(""),logistikNummer.getOrElse(""),leveringsPeriode,properties,installationsId) :: leveringsAftaler
    }
   /* val provisioneringsSystem = findProperty(p.properties,Properties.ProvSystem)
    val provisioneringsNummer = findProperty(p.properties,Properties.ProvNum)
    val logistikNummer = findProperty(p.properties,Properties.LogistikNum)
    leveringsAftaler = opretLeveringsAftale(abonId,forbruger,p.id,provisioneringsSystem.getOrElse(""),provisioneringsNummer.getOrElse(""),logistikNummer.getOrElse(""),leveringsPeriode,properties) :: leveringsAftaler*/
    leveringsAftaler
  }

  def findAlleBundleProdukter(p : Produkt) : List[Int] = {
    var bpsAll = List[Int]()
    def addBp(bl : List[Int], bps : List[Int]) {
      for (bpId <- bl) {
        val bp = ProduktRepo.findProdukt(bpId)
        if (bp.bundleProducts == Nil) {
          bpsAll = bpId :: bpsAll
        } else {
          addBp(bp.bundleProducts,bpsAll)
        }
      }
    }
    addBp(p.bundleProducts,bpsAll)
    bpsAll
  }

  def findProperty(properties : Map[Properties.Value,String], property : Properties.Value) : Option[String] = {
    if (properties != null && properties.keySet.contains(property))
      Some(properties(property))
    else
      None
  }

  def cloneWithProvisonering(l : LeveringsAftale) : LeveringsAftale with Provisionering = {
    new LeveringsAftale(l.id,l.abonnementId,l.produktId,l.leveringsPeriode,l.forbruger,l.properties,l.status,l.installationsId,l.opgraderLeveringsAftaleId) with Provisionering
  }
}