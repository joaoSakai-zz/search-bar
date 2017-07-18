function getContactOptions(name) {
    $.get('/contacts/' + name)
        .done((data) => {
            let result = JSON.parse(data.body);
            this.handleContactInfo(result);
        })
        .fail((error) => {
            console.error('Error on get contacts options: ', error);
        });   
}

function handleContactInfo(result) {
    let contactList = $('#contactList');
    if(result.length === 0) {
        let noResults = $('<li>  No results... </li>'); 
        contactList.append(noResults);
        contactList.show();
    } 
    $(result).each((index, contact) => {
        let contactElement = this.createContactElement(contact);
        contactList.append(contactElement);    
    });
    contactList.show();
}


function createContactElement(contact) {
    let contactElement = $('<li>' + contact.name + '</li>'); 
    contactElement.click((event) => {
        event.stopPropagation();
        $.get('/contacts/' + contact.id + '/details')
            .done((data) => {
                let result = JSON.parse(data.body);
                $('#contactName').text(result.name);
                $('#contactPhone').text(result.phone);
                $('#contactEmail').text(result.email);    
                $('#contact-details-wrapper').modal();
                this.clearContactsList();
                this.clearSearchInput();
            })
            .fail((error) => {
                console.error('Error on get contact details: ', error);
            })

    });
    return contactElement;
}

function clearSearchInput() {
    $('#searchInput').val('');
}

function clearContactsList() {
    let contactsList = $('#contactList');
    contactsList.hide();
    contactsList.empty();
}

$(() => {
    let searchInput = $('#searchInput');
    this.clearContactsList();
    searchInput.keyup((event) => {
        let searchName = searchInput.val();
        this.clearContactsList();
        if(searchInput.val().length >= 1) {
            this.getContactOptions(searchName);
        }
    });
});