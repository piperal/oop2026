import { useEffect, useState } from 'react'
import type { Athlete } from '../models/Athlete'
function Homepage() {

  const [athletes, setAthletes] = useState<Athlete[]>([])
  const [totalElements, setTotalElements] = useState(0);
  const [totalPages, setTotalPages] = useState();
  const [page, setPage] = useState(0);
  const [size, setSize] = useState(2);
  const [sort, setSort] = useState("id,asc")


  useEffect(() => {
    fetch(`http://localhost:5000/all?page=${page}&size=${size}&sort=${sort}`)
      .then(res => res.json())
      .then(json => {
        console.log(json)
        setAthletes(json.content);
        setTotalElements(json.totalElements);
        setTotalPages(json.totalPages)
      })
  }, [page, size, sort])

  const sizeHandler = (newSize: number) => {
    setSize(newSize);
    setPage(0)
  }
  const sortHandler = (newSort: string) => {
    setSort(newSort);
    setPage(0)
  }

  return (<>
    <div>
      {page * size + 1}-{(page + 1) * size > totalElements ? totalElements : (page + 1) * size}
      kuvatud {totalElements}-st
    </div>

    <select defaultValue={1} onChange={(e) => { sizeHandler(Number(e.target.value)) }}>
      <option>2</option>
      <option>3</option>
      <option>4</option>
    </select>
    <button onClick={() => { sortHandler("id,asc") }}>Sort older</button>
    <button onClick={() => { sortHandler("id,desc") }}>Sort newer</button>
    <button onClick={() => { sortHandler("results,desc") }}>Sort results (desc)</button>
    <button onClick={() => { sortHandler("results,asc") }}>Sort results (asc)</button>
    <table>
      <thead>
        <tr>
          <th>First Name</th>
          <th>Last Name</th>
          <th>Number</th>
          <th>Results</th>
        </tr>
      </thead>
      <tbody>
        {athletes.map(athlete =>
          <tr key={athlete.id}>
            <td>{athlete.firstName}</td>
            <td>{athlete.lastName}</td>
            <td>{athlete.number}</td>
            <td>{athlete.results[0].result}</td>
          </tr>)}
      </tbody>
    </table>

    <button disabled={page === 0} onClick={() => { setPage(page - 1) }}>Previous</button>
    <span>{page + 1}/ {totalPages}</span>
    <button disabled={page + 1 === totalPages} onClick={() => { setPage(page + 1) }}>Next</button>
  </>
  )
}

export default Homepage