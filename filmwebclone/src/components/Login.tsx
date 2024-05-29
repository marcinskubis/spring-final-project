import { Link } from "react-router-dom";
import Button from "./SubmitFormButton";
import { FormEvent, useState } from "react";
import { useNavigate } from "react-router-dom";

export default function Login() {
  const [inputs, setInputs] = useState({ userName: "", password: "" });
  const navigate = useNavigate();

  const handleChange = (e: any) => {
    const { name, value } = e.target;
    setInputs((prevVal) => {
      return {
        ...prevVal,
        [name]: value,
      };
    });
  };
  return (
    <form
      autoComplete="off"
      className="flex flex-col gap-4 size-fit m-4 text-4xl  text-white"
      onSubmit={(e: FormEvent) => {
        e.preventDefault();
        console.log(inputs);

        try {
          fetch("http://localhost:8080/auth/login", {
            method: "POST",
            headers: {
              "Content-Type": "application/json",
            },
            body: JSON.stringify({
              username: "123",
              password: "123",
            }),
            credentials: "include",
            mode: "no-cors",
          }).then(async (res) => {
            res = await res.json();
            console.log(res);
          });
        } catch (e) {
          console.log(e);
        }

        if (true) {
          navigate("/mainpage");
        }
      }}
    >
      <div className="flex w-full justify-between">
        <div>Sign In</div>
        <Link to="/register" className="flex text-sm self-end text-orange-400">
          sign up
        </Link>
      </div>
      <label htmlFor="userName">
        <input
          name="userName"
          placeholder="user name"
          type="text"
          className=" outline-none text-[1rem] rounded-md text-black px-4 w-64"
          onChange={handleChange}
        />
      </label>
      <label htmlFor="password">
        <input
          name="password"
          type="password"
          placeholder="password"
          className=" outline-none text-[1rem] rounded-md text-black px-4 w-64"
          onChange={handleChange}
        />
      </label>

      <Button />
    </form>
  );
}
