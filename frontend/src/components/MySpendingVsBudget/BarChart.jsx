import { Bar, Line } from "react-chartjs-2"
import {
    Chart as ChartJS,
    CategoryScale,
    LinearScale,
    BarElement,
    Title,
    Tooltip,
    Legend
} from 'chart.js'

ChartJS.register(
    CategoryScale,
    LinearScale,
    BarElement,
    Title,
    Tooltip,
    Legend
)

export const BarChart = ({ labels, values }) => {
    console.log(labels, values)
    const data = {
        labels: labels,
        datasets: [
            {
                label: 'My Spendings V/s Budget',
                data: values,
                borderColor: "rgba(255, 255, 255, 0.2)",
                borderWidth: 1,
                backgroundColor: values.length === 2 ? [
                    "rgba(0, 200, 255, 0.7)",
                    "rgba(0, 230, 180, 0.7)",
                ] : "rgba(0, 230, 180, 0.7)",
            }
        ]
    }

    const options = {
        plugins: {
            legend: {
                labels: {
                    color: "#ffffff",
                }
            }
        },
        scales: {
            x: {
                ticks: {
                    color: "#e0e0e0", 
                },
            grid: {
                color: "rgba(255, 255, 255, 0.08)", 
            }
            },
            y: {
                ticks: {
                    color: "#ffffff",
                },
                grid: {
                    color: "rgba(255, 255, 255, 0.08)",
                }
            }
        }
    }

    return (
        <>
            <Bar options={options} data={data} />
        </>
    )
}