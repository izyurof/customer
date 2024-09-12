import http from 'k6/http';
import { check, sleep } from 'k6';

export const options = {
  scenarios: {
    fetch_data: {
      executor: 'ramping-vus',
      startVUs: 0,
      stages: [
        {duration: '15s', target: 5},
        {duration: '30s', target: 5},
        {duration: '15s', target: 0},
      ],
      gracefulRampDown: '15s',
        exec: 'fetchData',
    },
    populate_and_update_data: {
        executor: 'ramping-vus',
        startVUs: 0,
        stages: [
            {duration: '15s', target: 5},
            {duration: '30s', target: 5},
            {duration: '15s', target: 0},
        ],
        gracefulRampDown: '15s',
        exec: 'populateAndUpdateData',
    },
  },
  thresholds: {
    'http_req_duration{scenario:fetch_data}': ['p(95)<500'],
    'http_req_duration{scenario:populate_and_update_data}': ['p(95)<500'],
    'http_req_failed{scenario:fetch_data}': ['rate<0.01'],
    'http_req_failed{scenario:populate_and_update_data}': ['rate<0.01'],
    'checks{scenario:fetch_data}': ['rate>0.95'],
    'checks{scenario:populate_and_update_data}': ['rate>0.95'],
  },
};

const SERVICE_URL = 'http://app:8080/v1/customers';

export function fetchData() {

  const getAllCustomers = http.get(`${SERVICE_URL}`, {tags: {name: 'FetchAllCustomers'}});

  check(getAllCustomers,
      {'is status 200': (r) => r.status === 200},
      {name: 'FetchAllCustomers'}
  );
}

export function populateAndUpdateData() {
    const newCustomerDto = {
      name: 'test_name31',
      surname: 'test_surname31',
      countryDto: {
        id: 7,
        name: 'Andorra',
        countryCode: 'AND'
      },
      contactDetailsDto: {
        email: 'test_email31@gmail.com',
        telegramId: '@test_telegram_id31',
      }
    };

    const createCustomer = http.post(`${SERVICE_URL}`, JSON.stringify(newCustomerDto), {
        headers: {'Content-Type': 'application/json',},
        tags: {name: 'CreateCustomer'}
    });

    check(createCustomer,
        {'is status 201': (r) => r.status === 201},
        {name: 'CreateCustomer'}
    );

    const createdCustomerId = JSON.parse(createCustomer.body).id;

  const updateCustomerDto = {
    name: 'test_name31',
    surname: 'test_surname31',
    countryDto: {
      id: 9,
      name: 'Argentina',
      countryCode: 'ARG'
    },
    contactDetailsDto: {
      email: 'test_email31@gmail.com',
      telegramId: '@test_telegram_id31',
    }
  };

  const updateCustomer = http.patch(`${SERVICE_URL}/${createdCustomerId}/update/country`, JSON.stringify(updateCustomerDto), {
      headers: {'Content-Type': 'application/json',},
      tags: {name: 'UpdateCustomer'}
  });

  check(updateCustomer,
      {'is status 204': (r) => r.status === 204},
      {name: 'UpdateCustomer'}
  );
}
