import React, { useEffect, useState } from "react";
import { Link } from "react-router-dom";



function ApiBooks() {
    const [books, setBooks] = useState([]);
    useEffect(() => {
        async function fetchBooks() {
            try {
                const data = await fetch("http://localhost:8081/api/books/apibooks");
                const fetchData = await data.json();
                setBooks(fetchData);
            } catch (e) {
                console.error("Unable to fetch", e);
            }
        }
        fetchBooks();
    }, []);

    return (<>
        {books.slice(0, 10).map((book) => (
            <Link
                to={`/books/${book.bible_id}`}
                key={book.id}
                style={{
                    textDecoration: "none",
                    flex: "0 0 20%", // 5 cards per view
                    scrollSnapAlign: "start",
                }}
            >
                <div className="book-card p-2 shadow-sm" style={{ minWidth: "200px", height: "400px" }}>
                    {/* Book Image */}
                    {
                        <img
                            src={'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQvUWw9KzoDoh-AzEqxVnSXZju9_SUC-xZIaw&s'}
                            alt={book.title}
                            style={{ width: "30%", height: "300px", objectFit: "cover" }}
                        />
                    }

                    {/* Title */}
                    <h5 style={{ color: "#333", minHeight: "2.5rem" }}>{book.language}</h5>

                    {/* Description */}
                    <p style={{ color: "#555", fontSize: "0.85rem", minHeight: "3rem" }}>
                        {book.version || "No description available"}
                    </p>

                    {/* Shelf */}
                    <p style={{ color: "#000000ff", fontSize: "0.8rem", fontWeight: "bold" }}>
                        Shelf: {book.shelf || "N/A"}
                    </p>

                </div>
            </Link>
        ))}

    </>)
}

export default ApiBooks