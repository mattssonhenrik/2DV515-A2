import styles from "./MainPage.module.css"
import { useState } from "react"

function MainPage() {
    const [clusters, setClusters] = useState([])
    const [iterations, setIterations] = useState("1")

    async function fetchClusters() {
        try {
            const iterationsNumber = Number(iterations)
            if (!Number.isInteger(iterationsNumber) || iterationsNumber <= 0) {
                console.error("Iterations must be a positive integer")
                return
            }
            const res = await fetch(`http://localhost:8081/api/clusters?iterations=${iterationsNumber}`)
            const data = await res.json()

            const groupedBlogNamesPerCluster = Array.from(
                { length: data.numberOfClusters },
                () => []
            )

            for (let blogIndex = 0; blogIndex < data.blogNames.length; blogIndex++) {
                const clusterIndex = data.clusterIndexPerBlogIndex[blogIndex]
                groupedBlogNamesPerCluster[clusterIndex].push(data.blogNames[blogIndex])
            }

            console.log("Grouped clusters:", groupedBlogNamesPerCluster)

            setClusters(groupedBlogNamesPerCluster)
        } catch (error) {
            console.error("Failed to fetch clusters:", error)
        }
    }

    return (
        <>
            <h1>Clusters</h1>

            <label>
                Iterations:{" "}
                <input
                    value={iterations}
                    onChange={(e) => setIterations(e.target.value)}
                    placeholder="e.g. 5"
                />
            </label>

            <button onClick={fetchClusters}>Fetch clusters</button>

            {clusters.map((blogNames, clusterIndex) => (
                <div key={clusterIndex}>
                    <h3>Cluster {clusterIndex}</h3>
                    <ul className={styles.clusterList}>
                        {blogNames.map((name) => (
                            <li key={name}>{name}</li>
                        ))}
                    </ul>
                </div>
            ))}
        </>
    )
}

export default MainPage
