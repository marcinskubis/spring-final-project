import { useState } from "react";
import UpdateMovie from "./UpdateMovie";

type I = {
  id: number;
  title: string;
  description: string;
  releaseDate: string;
  directorName: string;
  actorNames: string[];
};

type Props = {
  hide: any;
  movieItem: I;
  movies: I[];
  setMovies: any;
};

export default function MovieInfo({
  hide,
  movieItem,
  movies,
  setMovies,
}: Props) {
  const [updateVisibility, setUpdateVisibility] = useState(false);
  return (
    <div className="flex fixed justify-center items-center  h-screen w-screen bg-[#000000EF] top-0 left-0 z-50">
      <div className="flex flex-col items-center rounded-xl min-w-[70%] min-h-[70%] bg-slate-900 border">
        <div className="flex justify-between w-full">
          {updateVisibility && (
            <UpdateMovie
              hide={setUpdateVisibility}
              movieItem={movieItem}
              setMovies={setMovies}
            />
          )}
          <div className="p-4">
            {JSON.parse(sessionStorage.getItem("role")!) === "ADMIN" && (
              <div className="flex gap-2">
                <button
                  className="flex justify-center items-center px-4 border-2 border-red-500 rounded-lg text-red-500"
                  onClick={() => {
                    try {
                      fetch(`http://localhost:8080/movies/${movieItem.id}`, {
                        method: "DELETE",
                        headers: {
                          "Content-Type": "application/json",
                          Authorization: `Bearer ${JSON.parse(
                            sessionStorage.getItem("token")!
                          )}`,
                        },
                        mode: "cors",
                      }).then((res: any) => {
                        console.log(res);
                        if (res.status === 200) {
                          setMovies((prevMovies: any) => {
                            const updatedMovies = prevMovies.filter(
                              (element: any) => element.id !== movieItem.id
                            );
                            return [...updatedMovies];
                          });
                        }
                      });
                    } catch (e) {
                      console.log(e);
                    }
                  }}
                >
                  Delete Movie
                </button>
                <button
                  className="flex justify-center items-center px-4 border-2 border-green-500 rounded-lg text-green-500"
                  onClick={() => {
                    setUpdateVisibility(true);
                  }}
                >
                  Update Movie
                </button>
              </div>
            )}
          </div>
          <button
            className="flex items-center justify-center border-2 rounded-full size-10 m-4"
            onClick={() => {
              hide(false);
            }}
          >
            <svg
              width="24"
              height="24"
              fill="none"
              viewBox="0 0 24 24"
              xmlns="http://www.w3.org/2000/svg"
            >
              <path
                d="m4.397 4.554.073-.084a.75.75 0 0 1 .976-.073l.084.073L12 10.939l6.47-6.47a.75.75 0 1 1 1.06 1.061L13.061 12l6.47 6.47a.75.75 0 0 1 .072.976l-.073.084a.75.75 0 0 1-.976.073l-.084-.073L12 13.061l-6.47 6.47a.75.75 0 0 1-1.06-1.061L10.939 12l-6.47-6.47a.75.75 0 0 1-.072-.976l.073-.084-.073.084Z"
                fill="#ffffff"
              />
            </svg>
          </button>
        </div>
        <div className="text-5xl text-yellow-400">
          {movieItem.title.toLocaleUpperCase()}
        </div>
        <div className="flex justify-between w-full px-16 mt-10">
          <div>Description:</div>
          <div>Release Date:</div>
        </div>
        <div className="flex justify-between w-full px-16 text-base mt-4 ">
          <div>{movieItem.description}</div>
          <div>{movieItem.releaseDate}</div>
        </div>
        <div className="w-full px-16 mt-10 opacity-50">Directed by:</div>
        <div className="w-full px-16 ">-{movieItem.directorName}</div>
        <div className="mt-20 text-yellow-400">Actors</div>
        <div className="flex flex-wrap justify-center w-full gap-5 mt-5">
          {movieItem.actorNames.map((item, index) => (
            <div key={index}>-{item}</div>
          ))}
        </div>
      </div>
    </div>
  );
}
