import {forwardRef, useEffect, useRef} from "react";
import {doPost, drawDot} from "../utils/scripts";

export const DotsCanvas = forwardRef(({setServerData, rValue, points, newDot}) => {
    const dotsCanvasRef = useRef(null);

    function handleDotsDrawing(event) {
        const canvas = dotsCanvasRef.current;

        const rect = canvas.getBoundingClientRect();
        const xClick = event.clientX - rect.left;
        const yClick = event.clientY - rect.top;

        const canvasCenterX = canvas.width / 2;
        const canvasCenterY = canvas.height / 2;

        const scale = canvasCenterX / 5;

        const xValue = (xClick - canvasCenterX) / scale;
        const yValue = -(yClick - canvasCenterY) / scale;
        const data = {
            x: parseFloat((xValue).toFixed(2)),
            y: parseFloat((yValue).toFixed(2)),
            r: parseInt(rValue)
        }


        const response = doPost("http://localhost:8080/checkHit", data);


        response.then(async response => {
            if (response.ok) {
                let data = await response.json();
                await setServerData(data);
                console.log(data.status)
                drawDot(dotsCanvasRef.current, data.x, data.y, data.status ? 'green' : 'red');
                setServerData(data);

            }
            else {
                setServerData({error: response.status})
            }

        })
    }

    useEffect(() => {
        if (points && points.length) {
            points.forEach(point => {
                drawDot(dotsCanvasRef.current, point.x, point.y, point.status ? 'green' : 'red');
            });
        }
    }, [points]);

    useEffect(() => {
        console.log("meow")
        console.log(newDot.x)
        console.log(newDot.status)
        drawDot(dotsCanvasRef.current, newDot.x * newDot.r, newDot.y * newDot.r, newDot.status ? 'green' : 'red');



    }, [newDot])



    return (

        <canvas id="dotsCanvas" ref={dotsCanvasRef} width={500} height={500} onClick={handleDotsDrawing}/>

    )


});