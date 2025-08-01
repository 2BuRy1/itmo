import {forwardRef, useState} from "react";


export const InputText = forwardRef((props, ref) => {
    return(
        <label htmlFor={props.id}>
            <input type ={props.type}
                   id={props.id}
                   placeholder={props.name}
                   value={props.value}
                   onChange={props.onChange}
                   required={true}
                   className={props.class}
                   ref={ref}
            />
        </label>
    )

})







