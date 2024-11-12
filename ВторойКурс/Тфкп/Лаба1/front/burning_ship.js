const ship_canvas = document.getElementById('burning_shipCanvas');
const ship_ctx = ship_canvas.getContext('2d');
const ship_width = ship_canvas.width;
const ship_height = ship_canvas.height;
const ship_start_real_x = -1.8;
const ship_start_real_y = -0.09;
const ship_real_width = 0.1;
const ship_real_height = ship_real_width * ship_height / ship_width;
const ship_color_gradient = [[0,0,0], [255,0,0], [255,102,0],[255,255,0],[255,255,255]];
const ship_color_gradient_len = [16, 8, 32, 128];
let ship_maxIter = 200;

function calc_ship(c1, c2) {
    let x = 0;
    let y = 0;
    for (let i = 0; i < ship_maxIter; i++) {
        let xtemp = x * x - y * y + c1;
        y = Math.abs(2 * x * y) + c2;
        x = xtemp;
        if (x * x + y * y > 4) {
            return i + 1;
        }
    }
    return 0;
}


function makeColormap(length, colors, bands) {
    const colorCount = colors.length;

    const bandTotal = bands.reduce((sum, val) => sum + val, 0);
    const bandBreakpoints = bands.reduce((acc, val, idx) => {
        const prevSum = idx > 0 ? acc[idx - 1] : 0;
        acc.push(Math.floor((prevSum + val) * length / bandTotal));
        return acc;
    }, []);


    let currentColor = 0;
    let bandIndex = 0;
    const colormap = Array(length);
    const bandSizes = [];

    for (let i = 0; i < length; i++) {
        while (bandBreakpoints[currentColor] <= i) {
            bandSizes.push(bandIndex);
            currentColor++;
            bandIndex = 0;
        }
        colormap[i] = [currentColor, bandIndex];
        bandIndex++;
    }
    bandSizes.push(bandIndex);

    const finalColormap = colormap.map(([col, band]) => {
        const lerpValue = bandSizes[col] <= 1 ? 1 : band / (bandSizes[col] - 1);
        const startColor = colors[col];
        const endColor = colors[col + 1];
        return [
            startColor[0] + (endColor[0] - startColor[0]) * lerpValue,
            startColor[1] + (endColor[1] - startColor[1]) * lerpValue,
            startColor[2] + (endColor[2] - startColor[2]) * lerpValue,
        ];
    });

    return finalColormap;
}

function pixel_to_real_cords(pixel_x, pixel_y) {
    const real_x = ((pixel_x * ship_real_width) / ship_width) + ship_start_real_x;
    const real_y = ((pixel_y * ship_real_height) / ship_height) + ship_start_real_y;
    return [real_x, real_y];
}

function set_rgb(color){
    return `rgb(${color[0]},${color[1]}, ${color[2]})`;
}

function drawBurning_ship() {
    const colorMap = makeColormap(ship_maxIter + 1, ship_color_gradient, ship_color_gradient_len)
    for (let ix = 0; ix < width; ix++) {
        for (let iy = 0; iy < height; iy++) {
            const [x, y] = pixel_to_real_cords(ix, iy);
            const iter = calc_ship(x, y)
            ship_ctx.fillStyle = set_rgb(colorMap[iter]);
            ship_ctx.fillRect(ix, iy, 1, 1);
        }
    }
}

drawBurning_ship();

document.getElementById('burniterSlider').addEventListener('input', (e) => {
    ship_maxIter = parseInt(e.target.value);
    setTimeout(() => {
        drawBurning_ship();
    }, 100)
});