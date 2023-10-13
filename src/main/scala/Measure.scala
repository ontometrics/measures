package com.ontometrics.measures

import scala.language.implicitConversions.*

import unitofmeasurements.mase._

//sealed trait Measure


case class Weight(amount: Double, unit: WeightUnit) extends Ordered[Weight] {
  override def compare(that: Weight): Int = {
    val thisInGrams = this.amount * this.unit.conversionFactorToGrams
    val thatInGrams = that.amount * that.unit.conversionFactorToGrams
    thisInGrams.compareTo(thatInGrams)
  }
}

trait Convertable[Weight]

given Convertable[Weight] with
  extension (weight: Weight)
    def toOunces: Weight = Weight(weight.amount/28.3495, OunceUnit)



//case class Gram(override val amount: Double) extends Weight(amount, GramUnit)
//
//case class Ounce(override val amount: Double) extends Weight(amount, OunceUnit)

sealed abstract class WeightUnit //extends Measurement
case object GramUnit extends WeightUnit

case object Kilogram extends WeightUnit

case object OunceUnit extends WeightUnit

case object Milligram extends WeightUnit

case object Microgram extends WeightUnit

case object Nanogram extends WeightUnit

case object Picogram extends WeightUnit

extension (unit: WeightUnit) def conversionFactorToGrams: Double =
  unit match {
    case GramUnit => 1.0
    case Kilogram => 1000.0
    case OunceUnit => 28.349523125
    case Milligram => 0.001
    case Microgram => 0.000001
    case Nanogram => 0.000000001
    case Picogram => 0.000000000001
  }


extension (amount: Double)
  def grams: Weight = Weight(amount, GramUnit)
 // def kilograms: Weight = Kilogram(amount)
  def ounces: Weight = Weight(amount, OunceUnit)

//given Conversion[Weight, Ounce] with
//  def apply(gram: Weight): Weight =
//    Weight((gram.amount / 28.349523125, GramUnit)

extension (grams: Weight)
  def asOunces: Weight = Weight(grams.amount/28.349523125, OunceUnit)