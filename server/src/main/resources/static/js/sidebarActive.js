$(function () {
    "use strict";
    var url = window.location + "";
    var path = url.replace(
      window.location.protocol + "//" + window.location.host + "/",
      ""
    );
    var element = $(".nav-list a").filter(function () {
      return this.href === url || this.href === path;
    }).attr('id', 'active');

  });