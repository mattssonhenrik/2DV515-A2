function MainPage() {
  async function fetchBlogData() {
    try {
      const res = await fetch("http://localhost:8081/api/data/blogdata")
      if (!res.ok) {
        console.log("Somethings wrong with the result!")
      }
      const data = await res.json()
      console.log("BlogDataToMemory from backend:", data)
    } catch (error) {
      console.error("Failed to fetch blogdata:", error)
    }
  }
  return (
    <>
      <h1>Lets go from Main</h1>
      <button onClick={fetchBlogData}>Fetch blogdata</button>
    </>
  )
}

export default MainPage
