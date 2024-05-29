import { useState } from "react";
import AddMovieComp from "./AddMovieComp";
import MovieCart from "./MovieCard";

export default function MainPage() {
  const [showTemplate, setShowTemplate] = useState<boolean>(false);
  return (
    <div className="flex flex-col relative text-3xl text-white gap-2 items-center w-full min-h-screen">
      <div className="flex flex-col w-fit h-full">
        <div className="flex items-center justify-between w-full my-4 px-8">
          <div>Movies</div>
          {(JSON.parse(sessionStorage.getItem("role")!) === "ADMIN" ||
            true) && (
            <button
              className="flex my-2 border-4 border-orange-700 rounded-lg text-base w-fit self-center px-4 py-2 bg-orange-500 hover:scale-110 ease-in-out duration-150"
              onClick={() => {
                setShowTemplate(true);
              }}
            >
              Add Movie
            </button>
          )}
        </div>
        <div className="flex flex-wrap gap-2 justify-center mt-20 border h-full p-6">
          {[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14].map((item) => (
            <MovieCart key={item} />
          ))}
        </div>
      </div>
      {showTemplate && <AddMovieComp hide={setShowTemplate} />}
    </div>
  );
}
