

export function Button(props) {

    return(
        <input type="button"
               onClick={props.onClick}
               value={props.value}
               className={props.class}
               id= {props.id} />
    )


}