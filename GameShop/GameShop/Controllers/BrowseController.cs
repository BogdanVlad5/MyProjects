using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using GameShop.Models;

namespace GameShop.Controllers
{
    public class BrowseController : Controller
    {
        private GameDBContext db = new GameDBContext();
        //
        // GET: /ShoppingCart/

        public ActionResult Index()
        {
            ViewBag.listProducts = db.Games.ToList();
            return View();
        }

    }
}
