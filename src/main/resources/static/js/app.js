var stompClient = Stomp.over(new SockJS('/ws'));
stompClient.connect({}, function () {
    stompClient.subscribe('/topic/woofs', function (output) {
        document.getElementById("container").insertBefore(
            createWoof(JSON.parse(output.body)),
            container.firstChild);
    })
})

function createWoof(woof) {
    console.log(woof);
    let div = document.createElement("div");
    div.className = "media mb-3 border-bottom-1 shadow p-3";
    div.innerHTML = `<img class="mr-3 align-self-start" style="max-height: 64px; max-width: 64px;" src="${woof.dog.profile_picture}">
<div class="media-body">
<a href="/${woof.dog.id}"><h5 class="mt-0">${woof.dog.name}</h5></a>
<h5 class="mt-0">${woof.dog.breed}</h5>
<p class="mt-0">${woof.message}</p>
</div>
`;
    return div;
}