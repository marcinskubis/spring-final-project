import { FormEvent, useEffect, useState } from "react";
import Button from "./SubmitFormButton";

type D = {
  id: number;
  movieTitles: number[];
  seriesTitles: string[];
  name: string;
};

type I = {
  title: string;
  description: string;
  releaseDate: Date;
  directorId: number;
  actorIds: number[];
};

export default function AddMovieComp({ hide, setMovies }: any) {
  const [directors, setDirectors] = useState<D[]>([]);
  const [actors, setActors] = useState<D[]>([]);
  const [addedActors, setAddedActors] = useState<number[]>([]);
  const [inputs, setInputs] = useState<I>({
    title: "",
    description: "",
    releaseDate: new Date(),
    directorId: 0,
    actorIds: [],
  });
  const handleChange = (e: any) => {
    const { name, value } = e.target;
    setInputs((prevVal) => {
      return {
        ...prevVal,
        [name]: value,
      };
    });
  };

  useEffect(() => {
    try {
      fetch("http://localhost:8080/directors", {
        method: "GET",
        headers: {
          "Content-Type": "application/json",
        },

        mode: "cors",
      })
        .then(async (res) => {
          const r = await res.json();
          return r;
        })
        .then((response: any) => {
          console.log(response);
          setDirectors(response);
        });
    } catch (e) {
      console.log(e);
    }
    try {
      fetch("http://localhost:8080/actors", {
        method: "GET",
        headers: {
          "Content-Type": "application/json",
        },

        mode: "cors",
      })
        .then(async (res) => {
          const r = await res.json();
          return r;
        })
        .then((response: D[]) => {
          setActors(response);
        });
    } catch (e) {
      console.log(e);
    }
  }, []);

  useEffect(() => {
    console.log("dir", directors);
    console.log("act", actors);
    console.log("inputs", inputs);
    console.log("addedActors", addedActors);
  }, [directors, actors, inputs, addedActors]);
  return (
    <form
      autoComplete="off"
      className="flex flex-col justify-center items-center fixed gap-4 text-4xl  text-white w-screen h-screen bg-[#000000EF]"
      onSubmit={(e: FormEvent) => {
        e.preventDefault();
        console.log(inputs);
        //api fetch here
        try {
          fetch("http://localhost:8080/movies", {
            method: "POST",
            headers: {
              "Content-Type": "application/json",
              Authorization: `Bearer ${JSON.parse(
                sessionStorage.getItem("token")!
              )}`,
            },
            body: JSON.stringify({
              title: inputs.title,
              description: inputs.description,
              releaseDate: inputs.releaseDate,
              directorId: inputs.directorId,
              actorIds: addedActors,
            }),
            mode: "cors",
          })
            .then(async (res) => {
              const r = await res.json();
              return r;
            })
            .then((response) => {
              console.log(response);
              setMovies((prevMovies: any) => {
                return [...prevMovies, response];
              });
              hide(false);
            });
        } catch (e) {
          console.log(e);
        }
      }}
    >
      <div className="flex flex-col relative justify-center items-center size-full ">
        <button
          type="button"
          className="flex top-4 right-4 absolute items-center justify-center border-2 rounded-full size-10"
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
        <div className="flex flex-col relative items-center w-fit min-w-[65%] h-[60%] gap-4 border p-12 rounded-2xl bg-slate-900  overflow-y-auto">
          <div className="flex justify-between w-64">
            <div>Add movie</div>
          </div>
          <label htmlFor="title">
            <input
              name="title"
              placeholder="Enter Movie Name"
              type="text"
              className=" outline-none text-[1rem] rounded-md text-black px-4 w-64"
              onChange={handleChange}
            />
          </label>
          <label htmlFor="description">
            <textarea
              cols={14}
              rows={8}
              name="description"
              placeholder="Enter Movie Description"
              className=" outline-none text-[1rem] rounded-md text-black p-4 w-64 resize-none leading-6"
              onChange={handleChange}
            ></textarea>
          </label>
          <label htmlFor="releaseDate" className=" text-lg">
            Enter Movie Release Date
          </label>
          <div>
            <input
              name="releaseDate"
              type="date"
              placeholder="Enter Movie Release Date"
              className=" outline-none text-[1rem] rounded-md text-black px-4 w-64"
              onChange={handleChange}
            />
          </div>
          <select
            className="w-64 outline-none text-lg h-10 bg-transparent border px-2"
            onChange={(e) => {
              const { value } = e.target;
              const dirId = directors.find(
                (element) => element.name == value
              )?.id;
              dirId &&
                setInputs((prev) => {
                  return {
                    ...prev,
                    directorId: dirId,
                  };
                });
            }}
          >
            <option>Choose Director</option>
            {directors.map((item) => (
              <option key={item.id}>{item.name}</option>
            ))}
          </select>
          <div className="flex flex-col overflow-y-auto min-h-44 max-h-44 w-64 border p-2">
            {actors.map((item) => (
              <div className="flex gap-2 text-lg" key={item.id}>
                <input
                  type="checkbox"
                  id={item.name}
                  name={item.name}
                  onChange={(e) => {
                    const { checked } = e.target;
                    if (checked) {
                      setAddedActors((prev) => {
                        return [...prev, item.id];
                      });
                    } else {
                      setAddedActors((prev) => {
                        const newArr = prev.filter(
                          (element) => element !== item.id
                        );
                        return [...newArr];
                      });
                    }
                  }}
                />
                <label htmlFor={item.name}>{item.name}</label>
              </div>
            ))}
          </div>
          <Button />
        </div>
      </div>
    </form>
  );
}
