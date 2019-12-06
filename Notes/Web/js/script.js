function drawCircleGrey(ctx, radius, position) {
    ctx.beginPath();
    ctx.arc(position.x, position.y, radius, 0, 2 * Math.PI, false);
    ctx.fillStyle = hexToRGB(colorLuminance("#AF1E2D", Math.random() * -0.4), Math.random());
    if (Math.random() < 0.005 && radius < 2.5) {
        ctx.fillStyle = colorLuminance("#AF1E2D", Math.random() * 0.2);
    }
    ctx.fill();
}

function colorLuminance(hex, lum) {
    // validate hex string
    hex = String(hex).replace(/[^0-9a-f]/gi, '');
    if (hex.length < 6) {
        hex = hex[0] + hex[0] + hex[1] + hex[1] + hex[2] + hex[2];
    }
    lum = lum || 0;

    // convert to decimal and change luminosity
    let rgb = "#",
        c, i;
    for (i = 0; i < 3; i++) {
        c = parseInt(hex.substr(i * 2, 2), 16);
        c = Math.round(Math.min(Math.max(0, c + c * lum), 255)).toString(16);
        rgb += ("00" + c).substr(c.length);
    }
    return rgb;
}

function hexToRGB(hex, alpha) {
    let r = parseInt(hex.slice(1, 3), 16),
        g = parseInt(hex.slice(3, 5), 16),
        b = parseInt(hex.slice(5, 7), 16);

    if (alpha) {
        return "rgba(" + r + ", " + g + ", " + b + ", " + alpha + ")";
    } else {
        return "rgb(" + r + ", " + g + ", " + b + ")";
    }
}

function pointIsInPolygon(p, polygon) {
    let isInside = false;
    let minX = polygon[0].x,
        maxX = polygon[0].x;
    let minY = polygon[0].y,
        maxY = polygon[0].y;
    for (let n = 1; n < polygon.length; n++) {
        let q = polygon[n];
        minX = Math.min(q.x, minX);
        maxX = Math.max(q.x, maxX);
        minY = Math.min(q.y, minY);
        maxY = Math.max(q.y, maxY);
    }
    if (p.x < minX || p.x > maxX || p.y < minY || p.y > maxY) {
        return false;
    }
    let i = 0,
        j = polygon.length - 1;
    for (i, j; i < polygon.length; j = i++) {
        if (polygon[i].y > p.y != polygon[j].y > p.y &&
            p.x < (polygon[j].x - polygon[i].x) * (p.y - polygon[i].y) / (polygon[j].y - polygon[i].y) + polygon[i].x) {
            isInside = !isInside;
        }
    }
    return isInside;
}

function getBoundingBoxPolygon(points) {
    let minX = null;
    let maxX = null;
    let minY = null;
    let maxY = null;
    for (let i = 0; i < points.length; i++) {
        let x = points[i].x;
        let y = points[i].y;
        if (minX == null) {
            minX = x;
            maxX = x;
            minY = y;
            maxY = y;
        }
        minX = Math.min(minX, x);
        maxX = Math.max(maxX, x);
        minY = Math.min(minY, y);
        maxY = Math.max(maxY, y);
    }

    let width = maxX - minX;
    let height = maxY - minY;

    return {
        x: minX,
        y: minY,
        width: width,
        height: height
    };

}

function polygonScale(factor, polygon) {
    return polygon.map(function(data) {
        data.x = data.x * factor;
        data.y = data.y * factor;
        return data;
    });
}

function addParticle() {
    // Externa points
    let vector = {
        x: Math.random() * WIDTH,
        y: Math.random() * HEIGHT
    };

    let vector_check = {
        x: vector.x - offsetX,
        y: vector.y - offsetY
    };


    if (!pointIsInPolygon(vector_check, p1) 
      && !pointIsInPolygon(vector_check, p2) 
      && !pointIsInPolygon(vector_check, R) 
      && !pointIsInPolygon(vector_check, E) 
      && !pointIsInPolygon(vector_check, D)) {
        drawCircleGrey(canvas, Math.random() * 5, {
            x: vector.x,
            y: vector.y
        });

    }
}


//
let WIDTH = $(window).width();
let HEIGHT = $(window).height();

let R = polygonScale(1, [{ "x": 103, "y": 127 }, { "x": 103, "y": 144 }, { "x": 103, "y": 191 }, { "x": 103, "y": 91 }, { "x": 110, "y": 74 },{ "x": 113, "y": 127 }, { "x": 113, "y": 143 }, { "x": 113, "y": 92 }, { "x": 121, "y": 125 }, { "x": 121, "y": 95 }, { "x": 125, "y": 105 }, { "x": 125, "y": 115 }, { "x": 128, "y": 191 }, { "x": 128, "y": 78 }, { "x": 131, "y": 139 }, { "x": 142, "y": 128 }, { "x": 142, "y": 91 }, { "x": 144, "y": 109 }, { "x": 147, "y": 191 }, { "x": 82, "y": 191 }, { "x": 82, "y": 74 }]);
let E = polygonScale(1, [{ "x": 159, "y": 74 }, { "x": 213, "y": 74 }, { "x": 213, "y": 92 }, { "x": 179, "y": 92 }, { "x": 179, "y": 123 }, { "x": 205, "y": 123 }, { "x": 205, "y": 140 }, { "x": 180, "y": 140 }, { "x": 180, "y": 174 }, { "x": 213, "y": 174 }, { "x": 213, "y": 191 }, { "x": 159, "y": 191 }]);
let D = polygonScale(1, [{ "x": 251, "y": 91 }, { "x": 262, "y": 91 }, { "x": 268, "y": 94 }, { "x": 272, "y": 100 }, { "x": 272, "y": 165 }, { "x": 269, "y": 172 }, { "x": 261, "y": 175 }, { "x": 251, "y": 175 }, { "x": 251, "y": 191 }, { "x": 269, "y": 191 }, { "x": 284, "y": 182 }, { "x": 292, "y": 164 }, { "x": 292, "y": 104 }, { "x": 285, "y": 85 }, { "x": 267, "y": 74 }, { "x": 231, "y": 74 }, { "x": 231, "y": 191 }, { "x": 251, "y": 191 }]);
let p1 = polygonScale(1, [{ "x": 53, "y": 0 }, { "x": 70, "y": 17 }, { "x": 40, "y": 59 }, { "x": 23, "y": 118 }, { "x": 27, "y": 168 }, { "x": 43, "y": 215 }, { "x": 70, "y": 249 }, { "x": 54, "y": 266 }, { "x": 19, "y": 220 }, { "x": 2, "y": 166 }, { "x": 0, "y": 111 }, { "x": 19, "y": 50 }]);
let p2 = polygonScale(1, [{ "x": 304, "y": 17 }, { "x": 321, "y": 0 }, { "x": 353, "y": 43 }, { "x": 373, "y": 100 }, { "x": 376, "y": 140 }, { "x": 364, "y": 201 }, { "x": 340, "y": 244 }, { "x": 320, "y": 266 }, { "x": 304, "y": 249 }, { "x": 342, "y": 191 }, { "x": 353, "y": 140 }, { "x": 349, "y": 102 }, { "x": 336, "y": 57 }]);

let tela = document.createElement('canvas');
tela.width = $(window).width();
tela.height = $(window).height();
$("body").append(tela);

let canvas = tela.getContext('2d');

let p1BB = getBoundingBoxPolygon(p1);
let rBB = getBoundingBoxPolygon(R);
let eBB = getBoundingBoxPolygon(E);
let dBB = getBoundingBoxPolygon(D);
let p2BB = getBoundingBoxPolygon(p2);
let offsetX = WIDTH / 2 - (p2BB.x + p2BB.width) / 2;
let offsetY = HEIGHT / 2 - p1BB.height / 2;

setInterval(function() {
    for (let i = 0; i < 100; i++) {
        addParticle();
    }
}, 10);


// X Y Letter
// 13  75  M
// 13  186 M
// 28  186 M
// 28  100 M
// 44  186 M
// 61  186 M
// 76  100 M
// 76  186 M
// 92  186 M
// 92  75  M
// 68  75  M
// 52  150 M
// 37  75  M
// 108 75  I
// 108 186 I
// 124 186 I
// 124 75  I
// 160 93  D
// 180 94  D
// 185 96  D
// 189 100 D
// 192 105 D
// 193 110 D
// 194 115 D
// 195 120 D
// 195 140 D
// 194 145 D
// 193 150 D
// 192 155 D
// 189 160 D
// 185 165 D
// 180 167 D
// 160 167 D
// 160 186 D
// 181 186 D
// 185 185 D
// 190 184 D
// 195 181 D
// 200 177 D
// 205 168 D
// 210 155 D
// 211 150 D
// 212 145 D
// 213 141 D
// 213 123 D
// 212 120 D
// 211 110 D
// 209 105 D
// 207 100 D
// 206 95  D
// 204 90  D
// 200 85  D
// 195 80  D
// 185 75  D
// 144 75  D
// 144 186 D
// 160 186 D
// 246 143 A
// 257 100 A
// 268 143 A
// 280 186 A
// 298 186 A
// 267 75  A
// 248 75  A
// 216 186 A
// 234 186 A
// 240 160 A
// 274 160 A
// 300 150 S
// 301 155 S
// 302 160 S
// 303 165 S
// 305 170 S
// 307 175 S
// 311 180 S
// 318 183 S
// 326 185 S
// 339 185 S
// 350 183 S
// 355 182 S
// 360 176 S
// 365 165 S
// 367 158 S
// 367 149 S
// 365 140 S
// 363 135 S
// 360 130 S
// 355 125 S
// 346 120 S
// 333 115 S
// 322 110 S
// 318 105 S
// 318 100 S
// 322 95  S
// 330 91  S
// 335 91  S
// 340 93  S
// 345 98  S
// 348 106 S
// 364 106 S
// 363 100 S
// 361 90  S
// 358 85  S
// 354 80  S
// 347 75  S
// 320 75  S
// 315 77  S
// 310 82  S
// 305 91  S
// 303 100 S
// 303 110 S
// 305 115 S
// 307 120 S
// 310 125 S
// 314 130 S
// 320 134 S
// 325 136 S
// 330 137 S
// 335 140 S
// 340 142 S
// 345 144 S
// 347 145 S
// 350 150 S
// 350 155 S
// 349 160 S
// 346 165 S
// 340 169 S
// 329 169 S
// 325 167 S
// 323 165 S
// 319 160 S
// 318 155 S
// 317 150 S
// 316 148 S
