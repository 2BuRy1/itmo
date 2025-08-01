import React, { useEffect, useState } from "react";
import "./styles/style.css"

export function PointsTable({ points, newPoint }) {
    const [tablePoints, setTablePoints] = useState([]);

    useEffect(() => {
        if (points && points.length) {
            setTablePoints([...points]);
        }
    }, [points]);

    useEffect(() => {
        if (newPoint && newPoint.x !== undefined && newPoint.y !== undefined) {
            setTablePoints((prevPoints) => {
                const isDuplicate = prevPoints.some(
                    (point) =>
                        point.x === newPoint.x &&
                        point.y === newPoint.y &&
                        point.r === newPoint.r
                );

                return isDuplicate ? prevPoints : [...prevPoints, newPoint];
            });
        }
    }, [newPoint]);

    return (
        <table className="points-table">
            <thead>
            <tr>
                <th>X</th>
                <th>Y</th>
                <th>R</th>
                <th>Status</th>
            </tr>
            </thead>
            <tbody>
            {(
                tablePoints.map((point, index) => (
                    <tr key={index} className={point.status ? "hit" : "miss"}>
                        <td>{point.x}</td>
                        <td>{point.y}</td>
                        <td>{point.r}</td>
                        <td>{point.status ? "Hit" : "Miss"}</td>
                    </tr>
                ))
            )}
            </tbody>
        </table>
    );
}