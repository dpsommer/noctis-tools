$(document).ready(function() {
  // size content pane initially
  resizeContentPane();
});

window.onresize = function(event) {
  resizeContentPane();
}

function resizeContentPane() {
  var wh = $(window).height(),
      hh = $('.header').outerHeight(true),
      fh = $('.footer').outerHeight(true),
      ph = $('.push').outerHeight(true),
      nh = $('.nav-tabs').outerHeight(true),
      height = wh - hh - fh - ph - nh;
  $('.tab-pane').css({
    'height' : height
  });
  if ($(window).width() < 1100) {
    $('body').css({
      'padding' : 0
    });
  } else {
    $('body').css({
      'padding' : '0 15% 0 15%'
    });
  }
}