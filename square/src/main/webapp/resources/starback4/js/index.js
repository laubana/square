var App = App || {};


App.init = function() {
	App.Sky.init();
};


App.Sky = (function() {
	var canvas = document.getElementById("canvas");
	var ctx = canvas.getContext("2d");

	var W = $(window).width();
	var H = $(window).height();

	var pxs = new Array();
	var rint = 60;

	var init = function() {
		$(canvas).attr("width", W).attr("height", H);

		for(var i = 0; i < 100; i++) {
			pxs[i] = new star();
			pxs[i].reset();
		}

		setInterval(draw,rint);
	};


	var draw = function() {
		ctx.clearRect(0,0,W,H);
		for(var i = 0; i < pxs.length; i++) {
			pxs[i].fade();
			pxs[i].move();
			pxs[i].draw();
		}
	};


	var star = function() {
		this.settings = {
			time_to_live: 8000,
			x_maxspeed: 5,
			y_maxspeed: 2,
			radius_max: 8,
			rt: 1,
			x_origin: 960,
			y_origin: 540,
			random: true,
			blink: true
		}

		this.reset = function() {
			this.x = this.settings.random ? W * Math.random() : this.settings.x_origin;
			this.y = this.settings.random ? H * Math.random() : this.settings.y_origin;
			this.r = ((this.settings.radius_max - 1)*Math.random()) + 1;
			this.dx = (Math.random() * this.settings.x_maxspeed) * (Math.random() < .5 ? -1 : 1);
			this.dy = (Math.random() * this.settings.y_maxspeed) * (Math.random() < .5 ? -1 : 1);
			this.hl = (this.settings.time_to_live / rint) * (this.r / this.settings.radius_max);
			this.rt = Math.random() * this.hl;
			this.settings.rt = Math.random() + 1;
			this.stop = Math.random() * .2 + .4;
			this.settings.xdrift *= Math.random() * (Math.random() < .5 ? -1 : 1);
			this.settings.ydrift *= Math.random() * (Math.random() < .5 ? -1 : 1);
		}

		this.fade = function() {
			this.rt += this.settings.rt;
		}

		this.draw = function() {
			if (this.settings.blink && (this.rt <= 0 || this.rt >= this.hl)) this.settings.rt = this.settings.rt * -1;
			else if(this.rt >= this.hl) this.reset();
			var new_opacity = 1 - (this.rt/this.hl);

			ctx.beginPath();
			ctx.arc(this.x, this.y, this.r, 0, Math.PI*2, true);
			ctx.closePath();

			g = ctx.createRadialGradient(this.x, this.y, 0, this.x, this.y, (this.r * new_opacity) <= 0 ? 1 : this.r * new_opacity);
			g.addColorStop(0, "rgba(255,255,255," + new_opacity + ")");
			g.addColorStop(this.stop, "rgba(77,101,181," + (new_opacity * .6) + ")");
			g.addColorStop(1, "rgba(77,101,181,0)");

			ctx.fillStyle = g;
			ctx.fill();
		}

		this.move = function() {
			this.x += (this.rt/this.hl) * this.dx;
			this.y += (this.rt/this.hl) * this.dy;

			if(this.x > W || this.x < 0) this.dx *= -1;
			if(this.y > H || this.y < 0) this.dy *= -1;
		}
	};


	return {
		init:init
	};
}());


$(function() {
    App.init();
});