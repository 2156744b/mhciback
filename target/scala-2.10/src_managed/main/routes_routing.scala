// @SOURCE:/home/crunchbang/documents/MobHCI/mhciback/conf/routes
// @HASH:e30ae579edafa7c15b56291051d320f5e152bfc6
// @DATE:Wed Mar 11 03:14:48 GMT 2015


import play.core._
import play.core.Router._
import play.core.j._

import play.api.mvc._
import play.libs.F

import Router.queryString

object Routes extends Router.Routes {

private var _prefix = "/"

def setPrefix(prefix: String) {
  _prefix = prefix
  List[(String,Routes)]().foreach {
    case (p, router) => router.setPrefix(prefix + (if(prefix.endsWith("/")) "" else "/") + p)
  }
}

def prefix = _prefix

lazy val defaultPrefix = { if(Routes.prefix.endsWith("/")) "" else "/" }


// @LINE:6
private[this] lazy val controllers_Application_index0 = Route("GET", PathPattern(List(StaticPart(Routes.prefix))))
        

// @LINE:7
private[this] lazy val controllers_Application_postgisVersion1 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("postgis"))))
        

// @LINE:8
private[this] lazy val controllers_Application_register2 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("register"))))
        

// @LINE:9
private[this] lazy val controllers_Application_addFriend3 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("addFriend"))))
        

// @LINE:10
private[this] lazy val controllers_Application_nearbyPublicEvents4 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("nearbyPublicEvents"))))
        

// @LINE:13
private[this] lazy val controllers_Assets_at5 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("assets/"),DynamicPart("file", """.+""",false))))
        
def documentation = List(("""GET""", prefix,"""controllers.Application.index()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """postgis""","""controllers.Application.postgisVersion()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """register""","""controllers.Application.register()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """addFriend""","""controllers.Application.addFriend()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """nearbyPublicEvents""","""controllers.Application.nearbyPublicEvents()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """assets/$file<.+>""","""controllers.Assets.at(path:String = "/public", file:String)""")).foldLeft(List.empty[(String,String,String)]) { (s,e) => e.asInstanceOf[Any] match {
  case r @ (_,_,_) => s :+ r.asInstanceOf[(String,String,String)]
  case l => s ++ l.asInstanceOf[List[(String,String,String)]] 
}}
      

def routes:PartialFunction[RequestHeader,Handler] = {

// @LINE:6
case controllers_Application_index0(params) => {
   call { 
        invokeHandler(controllers.Application.index(), HandlerDef(this, "controllers.Application", "index", Nil,"GET", """ Home page""", Routes.prefix + """"""))
   }
}
        

// @LINE:7
case controllers_Application_postgisVersion1(params) => {
   call { 
        invokeHandler(controllers.Application.postgisVersion(), HandlerDef(this, "controllers.Application", "postgisVersion", Nil,"GET", """""", Routes.prefix + """postgis"""))
   }
}
        

// @LINE:8
case controllers_Application_register2(params) => {
   call { 
        invokeHandler(controllers.Application.register(), HandlerDef(this, "controllers.Application", "register", Nil,"POST", """""", Routes.prefix + """register"""))
   }
}
        

// @LINE:9
case controllers_Application_addFriend3(params) => {
   call { 
        invokeHandler(controllers.Application.addFriend(), HandlerDef(this, "controllers.Application", "addFriend", Nil,"POST", """""", Routes.prefix + """addFriend"""))
   }
}
        

// @LINE:10
case controllers_Application_nearbyPublicEvents4(params) => {
   call { 
        invokeHandler(controllers.Application.nearbyPublicEvents(), HandlerDef(this, "controllers.Application", "nearbyPublicEvents", Nil,"POST", """""", Routes.prefix + """nearbyPublicEvents"""))
   }
}
        

// @LINE:13
case controllers_Assets_at5(params) => {
   call(Param[String]("path", Right("/public")), params.fromPath[String]("file", None)) { (path, file) =>
        invokeHandler(controllers.Assets.at(path, file), HandlerDef(this, "controllers.Assets", "at", Seq(classOf[String], classOf[String]),"GET", """ Map static resources from the /public folder to the /assets URL path""", Routes.prefix + """assets/$file<.+>"""))
   }
}
        
}

}
     