function updateTime() {
    const timeElement = document.getElementById('time');
    const now = new Date();
    timeElement.innerHTML = now.toLocaleDateString('ru-RU', {
        day: '2-digit',
        month: '2-digit',
        year: 'numeric'
    }) + ' ' + now.toLocaleTimeString('ru-RU') ;
}


updateTime();

setInterval(updateTime, 1000);



