package controllers

import play.api.mvc._
import play.api.data._
import play.api.data.Forms._

import views._
import models.Person

object Persons extends Controller {

  val personForm = Form(
    mapping(
      "name" -> text
    )(Person.apply)(Person.unapply)
  )

  def index(name: String) = Action {
    Ok(html.editPerson(personForm.fill(Person(name))))
  }

  def editPerson = Action {
    implicit request =>
      personForm.bindFromRequest.fold(
        formWithErrors => BadRequest(html.editPerson(formWithErrors)),
        person => Ok(html.person(person))
      )
  }
}
