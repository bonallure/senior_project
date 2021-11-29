import React from "react";

const Button = ({ type, id, btnName, onClick, style }) => {
    return (
        <>
            <button
                type = {type}
                id = {id}
                onClick = {onClick}
                style = {style}
            >
                {btnName}
            </button>
        </>
    );
};

export default Button;