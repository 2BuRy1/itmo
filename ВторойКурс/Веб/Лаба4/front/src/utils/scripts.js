export function drawDot(canvas, x, y, color) {
    const ctx = canvas.getContext("2d");

    const canvasCenterX = canvas.width / 2;
    const canvasCenterY = canvas.height / 2;

    const scale = canvasCenterX / 5; // Масштаб зависит от радиуса R
    const plotX = x * scale;        // Преобразование координат
    const plotY = -y * scale;

    ctx.beginPath();
    ctx.translate(canvasCenterX, canvasCenterY);
    ctx.arc(plotX, plotY, 5, 0, 2 * Math.PI); // Рисуем точку
    ctx.fillStyle = color;
    ctx.fill();
    ctx.resetTransform();
    ctx.closePath();
}


export function doPost(uri, data){

    let requestContent = {
        method: 'POST',

        headers: {
            'content-type': 'application/json',
            'Authorization': 'Bearer ' + localStorage.getItem("jwt")
        },
        body: JSON.stringify({
            x: data.x,
            y: data.y,
            r: data.r,
        })



    }
    return fetch(uri, requestContent)


}